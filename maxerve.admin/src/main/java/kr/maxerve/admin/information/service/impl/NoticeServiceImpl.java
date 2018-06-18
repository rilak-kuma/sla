package kr.maxerve.admin.information.service.impl;

import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.file.dao.FileDAO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.information.dao.NoticeDAO;
import kr.maxerve.admin.information.service.NoticeService;
import kr.maxerve.admin.information.vo.NoticeIVO;
import kr.maxerve.admin.information.vo.NoticeMVO;
import kr.maxerve.admin.information.vo.NoticeUVO;
import kr.maxerve.admin.information.vo.ReqNoticeVO;
import kr.maxerve.admin.tag.dao.TagDAO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.NoticeDTO;
import kr.maxerve.dto.TagDTO;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {

	@Resource(name="noticeDAO")
	private NoticeDAO noticeDAO;
	
	@Resource(name="tagDAO")
	private TagDAO tagDAO;

	@Resource(name="fileDAO")
	private FileDAO fileDAO;
	
	@Resource(name="propertiesService")
	private Properties propertiesService;

	@Resource(name="fileService")
	private FileService fileService;

	@Override
	public List<NoticeMVO> selectNoticelist(ReqNoticeVO vo) throws Exception {

		PaginationInfo pageInfo = new PaginationInfo();

		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(noticeDAO.selectNoticelistCnt(vo));											// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return noticeDAO.selectNoticelist(vo);
	}

	@Override
	public void insertNotice(NoticeIVO vo, AttachTagDTO attachTagDTO, AttachFileDTO atcFileDTO) throws Exception {

		NoticeDTO dto = vo;
		dto.setBody(fileService.moveEditor(vo.getBody()));
		noticeDAO.insertNotice(dto);
		
		String ntcIdx = noticeDAO.selectNoticeIdx(dto);
		
		//파일업로드처리
		atcFileDTO.setAtcLocCd(CommonCodeUtil._010._012.getValue());
		atcFileDTO.setAtcLocIdx(ntcIdx);
		
		StringTokenizer filePath = new StringTokenizer(atcFileDTO.getFilePath(), ",");
		Integer f = 1;
        while(filePath.hasMoreElements()){
        	String moveFile = fileService.moveFile(filePath.nextToken());
        	
			atcFileDTO.setOrd(f.toString());
			atcFileDTO.setFilePath(moveFile);
			atcFileDTO.setFileInfo("");
			fileDAO.insertAtcFile(atcFileDTO);
			f++;
        }
		
		//태그등록
        attachTagDTO.setTagLocCd(CommonCodeUtil._010._012.getValue());
        attachTagDTO.setTagLocIdx(ntcIdx);
		
		if(StringUtils.isNotEmpty(attachTagDTO.getTagNm())){
			Integer i = 1;
			StringTokenizer tagNms = new StringTokenizer(attachTagDTO.getTagNm(), ",");
			TagDTO tagDTO = new TagDTO();
			while(tagNms.hasMoreElements()){
				String tagNm = tagNms.nextToken();
				
				attachTagDTO.setOrd(i.toString());
				attachTagDTO.setTagNm(tagNm);
				
				tagDTO.setTagNm(tagNm);
				tagDAO.insertTag(tagDTO);
				tagDAO.insertAttachTag(attachTagDTO);
				i++;
			}
		}
	}

	@Override
	public NoticeMVO selectNoticeInfo(String ntcIdx) throws Exception {
		return noticeDAO.selectNoticeInfo(ntcIdx);
	}

	@Override
	public void updateNotice(NoticeUVO vo, AttachTagDTO attachTagDTO, AttachFileDTO atcFileDTO) throws Exception {

		NoticeDTO dto = vo;
		dto.setBody(fileService.moveEditor(vo.getBody()));
		noticeDAO.updateNotice(dto);
		
		//파일 업로드하기전 기존 첨부파일 삭제
		atcFileDTO.setAtcLocCd(CommonCodeUtil._010._012.getValue());
		atcFileDTO.setAtcLocIdx(dto.getNtcIdx());
		
		//기존 파일 삭제
		fileDAO.deleteAtcFile(atcFileDTO);
		
		//파일업로드처리
		StringTokenizer filePath = new StringTokenizer(atcFileDTO.getFilePath(), ",");
		Integer f = 1;                                                                                         
        while(filePath.hasMoreElements()){
        	String moveFile = fileService.moveFile(filePath.nextToken());
        	
			atcFileDTO.setOrd(f.toString());
			atcFileDTO.setFilePath(moveFile);
			atcFileDTO.setFileInfo("");
			fileDAO.insertAtcFile(atcFileDTO);
			f++;
        }
		
		//태그 삭제 후 재등록
        attachTagDTO.setTagLocCd(CommonCodeUtil._010._012.getValue());
		attachTagDTO.setTagLocIdx(dto.getNtcIdx());
		attachTagDTO.setOrd("");
		tagDAO.deleteAttachTag(attachTagDTO);
		
		if(StringUtils.isNotEmpty(attachTagDTO.getTagNm())){
			Integer i = 1;
			StringTokenizer tagNms = new StringTokenizer(attachTagDTO.getTagNm(), ",");
			TagDTO tagDTO = new TagDTO();
			while(tagNms.hasMoreElements()){
				String tagNm = tagNms.nextToken();
				
				attachTagDTO.setOrd(i.toString());
				attachTagDTO.setTagNm(tagNm);
				
				tagDTO.setTagNm(tagNm);
				tagDAO.insertTag(tagDTO);
				tagDAO.insertAttachTag(attachTagDTO);
				i++;
			}
		}
	}

	@Override
	public void deleteNotice(NoticeDTO vo) throws Exception {
		noticeDAO.deleteNotice(vo);
	}
	
}
