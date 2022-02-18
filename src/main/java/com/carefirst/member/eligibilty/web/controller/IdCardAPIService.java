package com.carefirst.member.eligibilty.web.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.carefirst.nexus.gen.idcard.api.MemberIdcardApi;
import com.carefirst.nexus.gen.idcard.model.GetMemberIdcardResponse;

@Service
public class IdCardAPIService {

	private static final Logger LOG = LogManager.getLogger(IdCardAPIService.class);

	@Autowired
	@Qualifier("idCards")
	MemberIdcardApi idcardsApi;

	public ResponseEntity<Object> getIDCardDocument(String id, String Accept) {
		LOG.info(" inside getIDCardDocument");
		byte[] data = null;
		MediaType mediaType = MediaType.IMAGE_PNG;
		try {
			GetMemberIdcardResponse idcards = idcardsApi.getMemberIdcardDetails(id, true);
			LOG.info(" idcards response {}", idcards);

			if (idcards.getMemberIdcard() != null) {
				if (id.startsWith("ID_BACK")) {
					data = com.sun.org.apache.xerces.internal.impl.dv.util.Base64
							.decode(idcards.getMemberIdcard().getBackImage());
					return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(data);
				} else {
					data = com.sun.org.apache.xerces.internal.impl.dv.util.Base64
							.decode(idcards.getMemberIdcard().getFrontImage());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.ok().contentType(mediaType).body(data);
	}
	
	
	
	public byte[] getIDCardDocument2(String id, String Accept) {
		LOG.info(" inside getIDCardDocument2");
		byte[] data = null;
		MediaType mediaType = MediaType.IMAGE_PNG;
		try {
			GetMemberIdcardResponse idcards = idcardsApi.getMemberIdcardDetails(id, true);
			LOG.info(" idcards response {}", idcards);

			if (idcards.getMemberIdcard() != null) {
				if (id.startsWith("ID_BACK")) {
					data = com.sun.org.apache.xerces.internal.impl.dv.util.Base64
							.decode(idcards.getMemberIdcard().getBackImage());
				//	return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(data);
				} else {
					data = com.sun.org.apache.xerces.internal.impl.dv.util.Base64
							.decode(idcards.getMemberIdcard().getFrontImage());
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

}
