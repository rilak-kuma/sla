package kr.maxerve.admin.activity.service.impl;

import java.util.List;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;
import kr.maxerve.admin.activity.dao.SympathyTalkDAO;
import kr.maxerve.admin.activity.service.SympathyTalkService;
import kr.maxerve.admin.activity.vo.ReqSympathyTalkReferenceVO;
import kr.maxerve.admin.activity.vo.ReqSympathyTalkVO;
import kr.maxerve.admin.activity.vo.SympathyTalkIVO;
import kr.maxerve.admin.activity.vo.SympathyTalkMVO;
import kr.maxerve.admin.activity.vo.SympathyTalkReferenceMVO;
import kr.maxerve.admin.activity.vo.SympathyTalkUVO;
import kr.maxerve.admin.file.dao.FileDAO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.tag.dao.TagDAO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.AttachTagDTO;
import kr.maxerve.dto.SympathyTalkDTO;
import kr.maxerve.dto.SympathyTalkReferenceDTO;
import kr.maxerve.dto.TagDTO;

@Service("sympathyTalkService")
public class SympathyTalkServiceImpl implements SympathyTalkService{

	@Resource(name="sympathyTalkDAO")
	SympathyTalkDAO sympathyTalkDAO;

	@Resource(name="propertiesService")
	Properties propertiesService;

	@Resource(name="tagDAO")
	private TagDAO tagDAO;

	@Resource(name="fileDAO")
	private FileDAO fileDAO;

	@Resource(name="fileService")
	private FileService fileService;

	@Override
	public void insertFsw(SympathyTalkIVO vo) throws Exception {

		SympathyTalkDTO dto = vo;
		dto.setBody(fileService.moveEditor(vo.getBody()));
		sympathyTalkDAO.insertFsw(dto);

		String fswTalkIdx = sympathyTalkDAO.selectFswTalkIdx(dto);

		//연관 게시물 등록
		for(SympathyTalkReferenceDTO symDto : vo.getReferenceList()){
			//등록
			symDto.setFswTalkIdx(fswTalkIdx);
			sympathyTalkDAO.insertSympathyTalkReference(symDto);
		}

		//파일업로드처리
		AttachFileDTO atcFileDTO = new AttachFileDTO();
		atcFileDTO.setAtcLocCd(CommonCodeUtil._010._003.getValue());
		atcFileDTO.setAtcLocIdx(fswTalkIdx);

		StringTokenizer filePath = new StringTokenizer(vo.getFilePath(), ",");
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
        AttachTagDTO attachTagDTO = new AttachTagDTO();
		attachTagDTO.setTagLocCd(CommonCodeUtil._010._003.getValue());
		attachTagDTO.setTagLocIdx(fswTalkIdx);

		if(StringUtils.isNotEmpty(vo.getTagNm())){
			Integer i = 1;
			StringTokenizer tagNms = new StringTokenizer(vo.getTagNm(), ",");
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
	public List<SympathyTalkMVO> selectFswList(ReqSympathyTalkVO vo) throws Exception {

		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(sympathyTalkDAO.selectFswListCnt(vo));											// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return sympathyTalkDAO.selectFswList(vo);
	}

	@Override
	public List<SympathyTalkMVO> selectUseFswList() throws Exception {
		return sympathyTalkDAO.selectUseFswList();
	}

	@Override
	public SympathyTalkMVO selectFswInfo(String fswTalkIdx) throws Exception {
		return sympathyTalkDAO.selectFswInfo(fswTalkIdx);
	}

	@Override
	public void updateFsw(SympathyTalkUVO vo) throws Exception {

		SympathyTalkDTO dto = vo;
		dto.setBody(fileService.moveEditor(vo.getBody()));
		sympathyTalkDAO.updateFsw(dto);

		//연관 게시물 삭제 후 등록
		//삭제
		sympathyTalkDAO.deleteSympathyTalkReference(vo.getFswTalkIdx());
		//연관 게시물 등록
		for(SympathyTalkReferenceDTO symDto : vo.getReferenceList()){
			//등록
			sympathyTalkDAO.insertSympathyTalkReference(symDto);
		}

		//파일 삭제 후 업로드처리
		AttachFileDTO atcFileDTO = new AttachFileDTO();
		atcFileDTO.setAtcLocCd(CommonCodeUtil._010._003.getValue());
		atcFileDTO.setAtcLocIdx(dto.getFswTalkIdx());

		//기존 파일 삭제
		fileDAO.deleteAtcFile(atcFileDTO);

		//파일업로드처리
		StringTokenizer filePath = new StringTokenizer(vo.getFilePath(), ",");
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
        AttachTagDTO attachTagDTO = new AttachTagDTO();
		attachTagDTO.setTagLocCd(CommonCodeUtil._010._003.getValue());
		attachTagDTO.setTagLocIdx(dto.getFswTalkIdx());
		attachTagDTO.setOrd("");
		tagDAO.deleteAttachTag(attachTagDTO);

		if(StringUtils.isNotEmpty(vo.getTagNm())){
			Integer i = 1;
			StringTokenizer tagNms = new StringTokenizer(vo.getTagNm(), ",");
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
	public int selectMaxOrd() throws Exception {
		return sympathyTalkDAO.selectMaxOrd();
	}

	@Override
	public void deletetFsw(SympathyTalkDTO vo) throws Exception {
		sympathyTalkDAO.updateFsw(vo);
	}

	@Override
	public int selectSympathyTalkTotalCnt(ReqSympathyTalkVO vo) throws Exception {
		return sympathyTalkDAO.selectSympathyTalkTotalCnt(vo);
	}

	@Override
	public List<SympathyTalkReferenceMVO> selectSymTalkReferenceList(ReqSympathyTalkReferenceVO vo) throws Exception {

		PaginationInfo pageInfo = new PaginationInfo();
		pageInfo.setCurrentPageNo(NumberUtils.toInt(vo.getPageIndex(), NumberUtils.INTEGER_ONE));					// 페이지 번호
		pageInfo.setRecordCountPerPage(Integer.valueOf(propertiesService.getProperty("pagination.countPerPage")));	// 페이지 당 행수 설정
		pageInfo.setPageSize(Integer.valueOf(propertiesService.getProperty("pagination.pagePerBlock")));   			// 페이지 링크 수
		pageInfo.setTotalRecordCount(sympathyTalkDAO.selectSymTalkReferenceListCnt(vo));											// 전체 데이터 수
		vo.setPageInfo(pageInfo);

		return sympathyTalkDAO.selectSymTalkReferenceList(vo);
	}

	@Override
	public List<SympathyTalkReferenceMVO> selectUseSymTalkReferenceList(String fswTalkIdx) throws Exception {
		ReqSympathyTalkReferenceVO vo = new ReqSympathyTalkReferenceVO();
		vo.setFswTalkIdx(fswTalkIdx);
		return sympathyTalkDAO.selectSymTalkReferenceList(vo);
	}
}
