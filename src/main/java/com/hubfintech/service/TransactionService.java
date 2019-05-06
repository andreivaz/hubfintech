package com.hubfintech.service;

import com.hubfintech.model.Payload;
import com.hubfintech.model.ReturnPayload;

public interface TransactionService {
	public ReturnPayload debitar(Payload payload);
}
