package kr.maxerve.admin.file.service.impl;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import javax.imageio.stream.ImageOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.maxerve.admin.cmmn.vo.ReqFileVO;
import kr.maxerve.admin.editor.vo.EditorUploadVO;
import kr.maxerve.admin.file.dao.FileDAO;
import kr.maxerve.admin.file.service.FileService;
import kr.maxerve.admin.file.vo.FileMVO;
import kr.maxerve.admin.utils.CommonCodeUtil;
import kr.maxerve.admin.utils.FileUtil;
import kr.maxerve.admin.utils.ImageUtil;
import kr.maxerve.dto.AttachFileDTO;
import kr.maxerve.dto.FileDTO;

@Service("fileService")
public class FileServiceImpl implements FileService {

	@Resource(name="fileDAO")
	private FileDAO fileDAO;
	@Value("#{propertiesService['upload.url.root']}")
	String uploadUrlRoot;
	@Value("#{propertiesService['upload.path.root']}")
	String uploadPathRoot;
	@Value("#{propertiesService['upload.tmp.prefix']}")
	String uploadTmpPrefix;
	@Value("#{propertiesService['upload.thumb.prefix']}")
	String uploadThumbPrefix;

	public ReqFileVO editorUpload2(EditorUploadVO editorUploadVO) throws Exception {
		MultipartFile upload = editorUploadVO.getUpload();

		if (upload == null) {
			throw new FileNotFoundException();
		}

		ReqFileVO resultFile = new ReqFileVO();

		String originalFilename = upload.getOriginalFilename();
		String contentType = upload.getContentType();
		long size = upload.getSize();
		String filename = "/" + FileUtil.createFilename();

		resultFile.setCnttTyp(contentType);
		resultFile.setOrigFileNm(originalFilename);
		resultFile.setServerSubPath(uploadTmpPrefix);
		resultFile.setFilePath(filename);
		resultFile.setFileSize(String.valueOf(size));

		File file = new File(FileUtil.createNewFile(uploadTmpPrefix + filename));
		upload.transferTo(file);

		return resultFile;
	}

	public FileDTO editorUpload(EditorUploadVO editorUploadVO) throws Exception {
		MultipartFile upload = editorUploadVO.getUpload();

		if (upload == null) {
			throw new FileNotFoundException();
		}

		FileDTO resultFile = new FileDTO();

		String originalFilename = upload.getOriginalFilename();
		String contentType = upload.getContentType();
		long size = upload.getSize();
		String filename = FileUtil.createFilename();
		String addImagePath = ImageUtil.addImagePath();
		String fullPath = uploadTmpPrefix + "/" + addImagePath + "/" + filename;

		resultFile.setCnttTyp(contentType);
		resultFile.setOrigFileNm(originalFilename);
		resultFile.setFilePath(fullPath);
		resultFile.setFileSize(String.valueOf(size));

		File file = new File(FileUtil.createNewFile(uploadPathRoot + fullPath));
		upload.transferTo(file);

		return resultFile;
	}


	public List<FileDTO> uploadFile(ReqFileVO fileVO) throws Exception {

		MultipartFile uploadFile = fileVO.getReqUploadFile();

		if (uploadFile == null) {
			throw new FileNotFoundException();
		}

		String originalFilename = uploadFile.getOriginalFilename();
		String contentType = uploadFile.getContentType();
		long size = uploadFile.getSize();
		String filename = FileUtil.createFilename();
		String addImagePath = ImageUtil.addImagePath();
		String fullPath = uploadTmpPrefix + "/" + addImagePath + "/" + filename;

 		File file = new File(FileUtil.createNewFile(uploadPathRoot + fullPath));

 		while (!file.exists()) {
			Thread.sleep(100);

		}
 		uploadFile.transferTo(file);
		fileVO.setFilePath(fullPath);
		String uploadThumbPathUrl = uploadThumbPrefix + uploadTmpPrefix + "/" + addImagePath + "/" + filename;
		fileVO.setUploadThumbPathUrl(uploadThumbPathUrl);

		String thumnailType = fileVO.getThumnailType();
		if(FileUtil.ThumnailType.CROP.getValue().equals(thumnailType)) {
			createThumnailCrop(file, fileVO);
			fileVO.setTmnPath(uploadThumbPathUrl);
		}else {
			InputStream fileStream = new FileInputStream(file);

			try {
				BufferedImage originalImg = ImageIO.read(fileStream);
				fileVO.setWdth(String.valueOf(originalImg.getWidth()));
				fileVO.setHgth(String.valueOf(originalImg.getHeight()));
			} catch (Exception e) {
				fileVO.setWdth("0");
				fileVO.setHgth("0");
			}

		}
//		else if(thumnailType.equals(FileUtil.ThumnailType.RESIZE.getValue())) {
//			resizeThumImage(file, fileVO, FileUtil.ThumnailType.RESIZE.getValue());
//			fileVO.setTmnPath(fullPath.replace(uploadTmpPrefix, uploadTmpPrefix+ uploadThumbPrefix));
//		}else {//ThumnailType NONE
//			resizeThumImage(file, fileVO, FileUtil.ThumnailType.NONE.getValue());
//		}

		fileVO.setOrigFileNm(originalFilename);
		fileVO.setFileSize(String.valueOf(size));
		fileVO.setCnttTyp(contentType);

		if (contentType.startsWith("image")) {
			fileVO.setFileTypCd(CommonCodeUtil._003._001.getValue());
		} else if (contentType.startsWith("video")) {
			fileVO.setFileTypCd(CommonCodeUtil._003._002.getValue());
		} else if (contentType.startsWith("application/vnd")) {
			fileVO.setFileTypCd(CommonCodeUtil._003._003.getValue());
		} else {
			fileVO.setFileTypCd(CommonCodeUtil._003._005.getValue());
		}

		fileDAO.insertFile(fileVO);

		List<FileDTO> fileList = this.selectFileList(fileVO);

		String removeFilePath = fileVO.getRemoveFilePath();
		if(removeFilePath != null && !removeFilePath.isEmpty()) {
			FileDTO removeFile = new FileDTO();
			removeFile.setFilePath(removeFilePath);
			removeFile(removeFile);
		}
		return fileList;
	}


	public List<FileDTO> removeFile(FileDTO fileVO) throws Exception{

		List<FileDTO> fileList = this.selectFileList(fileVO);
			FileDTO selFile = null;
			if(fileList != null && fileList.size() > 0) {
				String filePath = "";
				String tmnPath = "";

				for (int i=0, len = fileList.size();  i< len; i++) {
					selFile = fileList.get(i);
					filePath = selFile.getFilePath();
					tmnPath = selFile.getTmnPath();

					if(!"".equals(tmnPath)) {
						FileUtils.deleteQuietly(new File(uploadPathRoot + "/" + tmnPath));
					}
					FileUtils.deleteQuietly(new File(uploadPathRoot + "/" + filePath));
					fileDAO.deleteFile(selFile);
				}
			}
		return fileList;
	}

	public FileDTO selectFile(FileDTO fileVO) throws Exception{
		return fileDAO.selectFile(fileVO);
	}

	public List<FileDTO> selectFileList(FileDTO fileVO) throws Exception{
		List<FileDTO> fileList = fileDAO.selectFileList(fileVO);
		return fileList;
	}

	public String moveFile(String filePath) throws Exception {
		if (filePath.indexOf(uploadTmpPrefix + "/") == -1) {
			return filePath;
		}

		String moveFile = "";

		try {
			FileDTO param = new FileDTO();
			param.setFilePath(filePath);

			FileDTO fileInfo = fileDAO.selectFile(param);

			String tmpFile = filePath;
			String tmpThumb = "";
			if (fileInfo != null) {
				tmpThumb = fileInfo.getTmnPath();
			}

			String moveThumb = "";
			if (!StringUtils.isEmpty(tmpThumb)) {
				moveThumb = tmpThumb.replace(uploadTmpPrefix + "/", "/");
				FileUtils.moveFile(new File(uploadPathRoot + tmpThumb), new File(uploadPathRoot + moveThumb));
			}

			moveFile = tmpFile.replace(uploadTmpPrefix + "/", "/");
			FileUtils.moveFile(new File(uploadPathRoot + tmpFile), new File(uploadPathRoot + moveFile));

			if (fileInfo != null) {
				ReqFileVO param1 = new ReqFileVO();
				param1.setFilePath(tmpFile);
				param1.setReqFilePath(moveFile);
				param1.setTmnPath(moveThumb);

				fileDAO.updateFile(param1);
			}
		} catch (Exception e) {
			moveFile = filePath;
		}

		return moveFile;
	}

	private void createThumnailCrop(File file, ReqFileVO fileVO) throws Exception
	{

		InputStream fileStream = new FileInputStream(file);
		BufferedImage originalImg = ImageIO.read(fileStream);

		fileVO.setWdth(String.valueOf(originalImg.getWidth()));
		fileVO.setHgth(String.valueOf(originalImg.getHeight()));

		int origWidth = originalImg.getWidth();
		int origHeight = originalImg.getHeight();

		int cropWidth = NumberUtils.toInt(fileVO.getMaxWidth());
		int cropHeight = NumberUtils.toInt(fileVO.getMaxHeight());

		int width1 = cropWidth;
		int height1 = cropWidth * origHeight / origWidth;

		int height2 = cropHeight;
		int width2 = origWidth * cropHeight / origHeight;

		if (width1 >= cropWidth && height1 >= cropHeight) {
			originalImg = resizeImage(originalImg, width1, height1);
		} else if (width2 >= cropWidth && height2 >= cropHeight) {
			originalImg = resizeImage(originalImg, width2, height2);
		}

//		int maxWidth =  Integer.parseInt(fileVO.getMaxWidth());
//		calculateCropArea(originalImg, fileVO);

//		CropImage cropImage = fileVO.getCropImage();
//		int targetWdth = cropImage.getTargetWdth();

//		if( targetWdth < maxWidth) {//이미지가 Wdth가 maxWidth 보다 작을때
//			long wdth = Long.parseLong(fileVO.getWdth());
//			long hgth = Long.parseLong(fileVO.getHgth());
//			if(wdth <= hgth) {//height long
//				originalImg = resizeImage(originalImg, maxWidth, (int)(hgth*maxWidth/wdth));
//			}else {//wide long
//				originalImg = resizeImage(originalImg, (int)(wdth*maxWidth/hgth), maxWidth);
//			}
//			calculateCropArea(originalImg, fileVO);
//		}

		String uploadThumbPathUrl = fileVO.getUploadThumbPathUrl();
		File thumbnail = new File(FileUtil.createNewFile(uploadPathRoot + uploadThumbPathUrl));

		while (!thumbnail.exists()) {
			Thread.sleep(100);

		}
        FileOutputStream fos = new FileOutputStream(thumbnail);

        ImageWriteParam iwparam  = new JPEGImageWriteParam(Locale.getDefault());
        iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        iwparam.setCompressionQuality(1f);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();// 용량도 적고 색도 자연스럽다.>>> 추천  , jpg 색이 좀더 진하고 용량이 좀더 늘어남.
        ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
        writer.setOutput(ios);
        writer.write(null, new IIOImage(cropImageToByte(originalImg, fileVO), null, null), iwparam);
        ios.close();
        fos.close();
        writer.dispose();
	}

	@SuppressWarnings("unused")
	private static void calculateCropArea(BufferedImage originalImg, ReqFileVO fileVO) throws Exception
	{
		int origWidth = originalImg.getWidth();
		int origHeight = originalImg.getHeight();

		int cropWidth = NumberUtils.toInt(fileVO.getMaxWidth());
		int cropHeight = NumberUtils.toInt(fileVO.getMaxHeight());

		int width1 = cropWidth;
		int height1 = cropWidth * origHeight / origWidth;

		int height2 = cropHeight;
		int width2 = origWidth * cropHeight / origHeight;

		if (width1 >= cropWidth && height1 >= cropHeight) {
			originalImg = resizeImage(originalImg, width1, height1);
		} else if (width2 >= cropWidth && height2 >= cropHeight) {
			originalImg = resizeImage(originalImg, width2, height2);
		}

		//get the center point for crop
//		CropImage cropImage = new CropImage();
//		cropImage.setCenterWdth(originalImg.getWidth() /2);
//		cropImage.setCenterhgth(originalImg.getHeight() /2);
//
//
//		//calculate crop area
//		fileVO.setWdth(String.valueOf(cropWidth));
//		fileVO.setHgth(String.valueOf(cropHeight));
//
//		cropImage.setCropWdth(cropWidth);
//		cropImage.setCropHgth(cropHeight);
//		cropImage.setTargetWdth(targetWdth);
//		cropImage.setTargetHgth(targetHgth);
//		fileVO.setCropImage(cropImage);
	}

	private static BufferedImage cropImageToByte(BufferedImage originalImg, ReqFileVO fileVO) throws Exception
	{
//		CropImage cropImage  = fileVO.getCropImage();
//		int cropWdth = cropImage.getCropWdth();
//		int cropHgth = cropImage.getCropHgth();
//		int centerWdth = cropImage.getCenterWdth();
//		int centerhgth = cropImage.getCenterhgth();
//		int targetWdth = cropImage.getTargetWdth();
//		int targetHgth = cropImage.getTargetHgth();
//
//		//processing image
//		BufferedImage cloneImage = new BufferedImage(targetWdth, targetHgth, BufferedImage.TYPE_INT_RGB);
//		Graphics2D g2d = cloneImage.createGraphics();
//		g2d.fillRect(0, 0, targetWdth, targetHgth);
/*
[성능↑, 품질↓] RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR
[성능,품질 권장] RenderingHints.VALUE_INTERPOLATION_BILINEAR
[성능↓, 품질↑] RanderingHints.VALUE_INTERPOLATION_BICUBIC
*/
//		g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
//		g2d.drawImage(originalImg, 0, 0, targetWdth, targetHgth,   centerWdth - Integer.valueOf(cropWdth /2) , centerhgth - Integer.valueOf(cropHgth /2), centerWdth + Integer.valueOf(cropWdth /2), centerhgth + Integer.valueOf(cropHgth /2), null);
//		g2d.dispose();

		int center_x = originalImg.getWidth() / 2;
		int center_y = originalImg.getHeight() / 2;
		int cropWidth = NumberUtils.toInt(fileVO.getMaxWidth());
		int cropHeight = NumberUtils.toInt(fileVO.getMaxHeight());

		int x = center_x - Integer.valueOf(cropWidth/2);
		int y = center_y - Integer.valueOf(cropHeight/2);

		BufferedImage cloneImage = originalImg.getSubimage(x, y, cropWidth, cropHeight);

		return cloneImage;
	}


    /**
     * This function resize the image file and returns the BufferedImage object that can be saved to file system.
     */
    private static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);

        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }

    @SuppressWarnings("unused")
	private static void resizeThumImage(File file, ReqFileVO fileVO, String ThumnailType) throws Exception
	{

		InputStream fileStream = new FileInputStream(file);
		BufferedImage originalImg = ImageIO.read(fileStream);

		//calculate crop area
		int imgWdth = originalImg.getWidth();
		int imgHgth = originalImg.getHeight();
		int maxWidth =  Integer.parseInt(fileVO.getMaxWidth());

		if(imgWdth > maxWidth) {//resize
			int resizeHgth = imgHgth*maxWidth/imgWdth;
			fileVO.setWdth(String.valueOf(maxWidth));
			fileVO.setHgth(String.valueOf(resizeHgth));
			originalImg = resizeImage(originalImg, maxWidth, resizeHgth);
		}else{
			fileVO.setWdth(String.valueOf(imgWdth));
			fileVO.setHgth(String.valueOf(imgHgth));
		}

		File resizePath = null;

		if(ThumnailType.equals(FileUtil.ThumnailType.RESIZE.getValue())) {
			String uploadThumbPathUrl = fileVO.getUploadThumbPathUrl();
			resizePath = new File(FileUtil.createNewFile(uploadThumbPathUrl));
        }else if(ThumnailType.equals(FileUtil.ThumnailType.NONE.getValue())){
        	String filePath = fileVO.getFilePath();
			resizePath = new File(FileUtil.createNewFile(filePath));
        }

        FileOutputStream fos = new FileOutputStream(resizePath);

        ImageWriteParam iwparam  = new JPEGImageWriteParam(Locale.getDefault());
        iwparam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        iwparam.setCompressionQuality(1f);

        ImageWriter writer = ImageIO.getImageWritersByFormatName("png").next();// 용량도 적고 색도 자연스럽다.>>> 추천  , jpg 색이 좀더 진하고 용량이 좀더 늘어남.
        ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
        writer.setOutput(ios);

        if(ThumnailType.equals(FileUtil.ThumnailType.RESIZE.getValue())) {
        	writer.write(null, new IIOImage(cloneImage(originalImg), null, null), iwparam);
        }else if(ThumnailType.equals(FileUtil.ThumnailType.NONE.getValue())){
        	writer.write(null, new IIOImage(originalImg, null, null), iwparam);
        }
        ios.close();
        fos.close();
        writer.dispose();
	}

    private static final BufferedImage cloneImage(BufferedImage image) {
	    BufferedImage cloneImage = new BufferedImage(image.getWidth(),image.getHeight(), image.getType());
	    Graphics2D g2d = cloneImage.createGraphics();
	    g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
	    g2d.drawImage(image, 0, 0, null);
	    g2d.dispose();
	    return cloneImage;
    }

	/* (non-Javadoc)
	 * @see kr.maxerve.file.service.FileService#selectAttachList(kr.maxerve.dto.AttachFileDTO)
	 */
	public List<FileMVO> selectAttachList(AttachFileDTO vo) throws Exception {
		return fileDAO.selectAttachList(vo);
	}

	public String moveEditor(String body) throws Exception {
		Pattern p = Pattern.compile("src=\"(" + uploadUrlRoot + uploadTmpPrefix + "/" + ".*?)\"", Pattern.DOTALL);
		Matcher m = p.matcher(body);

		while (m.find()) {
			String tmp = m.group(1).replaceAll(uploadUrlRoot + "/", "/");
			String move = moveFile(tmp);

			body = body.replaceAll(m.group(1), uploadUrlRoot + move);
		}

		return body;
	}



}
