package com.almundo.callCenter.controller.v1;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.almundo.callCenter.model.notice.Call;
import com.almundo.callCenter.model.notice.Notice;
import com.almundo.callCenter.service.NoticeService;

@RestController
@RequestMapping("/v1/notice")
public class NoticeController {

private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);

	@Autowired
	private NoticeService noticeService;
	
	@RequestMapping(value = "/call/", method = RequestMethod.POST)
    @ResponseBody
	public ResponseEntity<Notice> createCall(@RequestParam String clientId){
		logger.info("[NoticeController] createCall(" + clientId + ")");
		
		Notice attendingCall = noticeService.doNotice(clientId, Call.class);
		
		return new ResponseEntity<Notice>(attendingCall, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/call/attendCalls", method = RequestMethod.GET)
    @ResponseBody
	public ResponseEntity<List<Notice>> getAttendCalls(){
		logger.info("[NoticeController] getAttendCalls()");
		
		
		List<Notice> attendCalls = noticeService.getAllAttendNotice();
		
		return new ResponseEntity<List<Notice>>(attendCalls, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/call/rejectCalls", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Notice>> getRejectCalls(){
		logger.info("[NoticeController] getRejectCalls()");
		
		
		List<Notice> rejectCalls = noticeService.getAllRejectedNotice();
		
		return new ResponseEntity<List<Notice>>(rejectCalls, HttpStatus.OK);
	}
	
}
