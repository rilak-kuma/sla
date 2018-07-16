package kr.maxerve.dto;

/**
* SmsDTO
* @author LEEC.J
* @since 2018.06.12
* @version 1.0
* @see
*
* <pre>
* SMS
* em_tran
* << 개정이력(Modification Information) >>
*
* 수정일        수정자        수정내용
* ----------      --------       ----------------------------------
* 2018.06.12     LEEC.J        최초 생성
* </pre>
*/
public class SmsDTO {
	private String tran_pr = "";		// INT(11) NOT NULL AUTO_INCREMENT,
	private String tran_refkey = "";	// VARCHAR(40) NULL DEFAULT NULL,
	private String tran_id = "";		// VARCHAR(20) NULL DEFAULT NULL,
	private String tran_phone = "";		// VARCHAR(15) NOT NULL,
	private String tran_callback = "";	// VARCHAR(15) NULL DEFAULT NULL,
	private String tran_status = "";	// CHAR(1) NULL DEFAULT NULL,
	private String tran_date = "";		// DATETIME NOT NULL,
	private String tran_rsltdate = "";	// DATETIME NULL DEFAULT NULL,
	private String tran_reportdate = "";	// DATETIME NULL DEFAULT NULL,
	private String tran_rslt = "";		// CHAR(1) NULL DEFAULT NULL,
	private String tran_net = "";		// VARCHAR(3) NULL DEFAULT NULL,
	private String tran_msg = "";		// VARCHAR(255) NULL DEFAULT NULL,
	private String tran_etc1 = "";		// VARCHAR(64) NULL DEFAULT NULL,
	private String tran_etc2 = "";		// VARCHAR(16) NULL DEFAULT NULL,
	private String tran_etc3 = "";		// VARCHAR(16) NULL DEFAULT NULL,
	private String tran_etc4 = "";		// INT(11) NULL DEFAULT NULL,
	private String tran_type = "";		// INT(11) NOT NULL,

	public String getTran_pr() {
		return tran_pr;
	}
	public void setTran_pr(String tran_pr) {
		this.tran_pr = tran_pr;
	}
	public String getTran_refkey() {
		return tran_refkey;
	}
	public void setTran_refkey(String tran_refkey) {
		this.tran_refkey = tran_refkey;
	}
	public String getTran_id() {
		return tran_id;
	}
	public void setTran_id(String tran_id) {
		this.tran_id = tran_id;
	}
	public String getTran_phone() {
		return tran_phone;
	}
	public void setTran_phone(String tran_phone) {
		this.tran_phone = tran_phone;
	}
	public String getTran_callback() {
		return tran_callback;
	}
	public void setTran_callback(String tran_callback) {
		this.tran_callback = tran_callback;
	}
	public String getTran_status() {
		return tran_status;
	}
	public void setTran_status(String tran_status) {
		this.tran_status = tran_status;
	}
	public String getTran_date() {
		return tran_date;
	}
	public void setTran_date(String tran_date) {
		this.tran_date = tran_date;
	}
	public String getTran_rsltdate() {
		return tran_rsltdate;
	}
	public void setTran_rsltdate(String tran_rsltdate) {
		this.tran_rsltdate = tran_rsltdate;
	}
	public String getTran_reportdate() {
		return tran_reportdate;
	}
	public void setTran_reportdate(String tran_reportdate) {
		this.tran_reportdate = tran_reportdate;
	}
	public String getTran_rslt() {
		return tran_rslt;
	}
	public void setTran_rslt(String tran_rslt) {
		this.tran_rslt = tran_rslt;
	}
	public String getTran_net() {
		return tran_net;
	}
	public void setTran_net(String tran_net) {
		this.tran_net = tran_net;
	}
	public String getTran_msg() {
		return tran_msg;
	}
	public void setTran_msg(String tran_msg) {
		this.tran_msg = tran_msg;
	}
	public String getTran_etc1() {
		return tran_etc1;
	}
	public void setTran_etc1(String tran_etc1) {
		this.tran_etc1 = tran_etc1;
	}
	public String getTran_etc2() {
		return tran_etc2;
	}
	public void setTran_etc2(String tran_etc2) {
		this.tran_etc2 = tran_etc2;
	}
	public String getTran_etc3() {
		return tran_etc3;
	}
	public void setTran_etc3(String tran_etc3) {
		this.tran_etc3 = tran_etc3;
	}
	public String getTran_etc4() {
		return tran_etc4;
	}
	public void setTran_etc4(String tran_etc4) {
		this.tran_etc4 = tran_etc4;
	}
	public String getTran_type() {
		return tran_type;
	}
	public void setTran_type(String tran_type) {
		this.tran_type = tran_type;
	}
}
