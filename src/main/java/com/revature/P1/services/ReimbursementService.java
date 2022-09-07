package com.revature.P1.services;

import com.revature.P1.daos.ReimbursementDAO;
import com.revature.P1.dtos.requests.ReimbursementRequest;
import com.revature.P1.models.Reimbursement;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReimbursementService {
    private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public Reimbursement saveReimbursement(ReimbursementRequest r){
        String reimb_id = UUID.randomUUID().toString();
        String status_id = "200";

        String type_id = "";

        switch(r.getType()){
            case "FOOD":
                type_id = "300";
                break;
            default:
                throw new InvalidRequestException("\nInvalid type");
        }

        Reimbursement reimbursement = new Reimbursement(r.getAuthor_id(), reimb_id, status_id, type_id, LocalDateTime.now(), r.getAmount(), r.getDescription());

        reimbursementDAO.save(reimbursement);

        return reimbursement;
    }
}
