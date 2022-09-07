package com.revature.P1.services;

import com.revature.P1.daos.ReimbursementDAO;
import com.revature.P1.dtos.requests.ReimbursementRequest;
import com.revature.P1.models.Reimbursement;
import com.revature.P1.utils.custom_exceptions.InvalidRequestException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class ReimbursementService {
    private final ReimbursementDAO reimbursementDAO;

    public ReimbursementService(ReimbursementDAO reimbursementDAO) {
        this.reimbursementDAO = reimbursementDAO;
    }

    public Reimbursement saveReimbursement(ReimbursementRequest r){
        String reimb_id = UUID.randomUUID().toString();
        String status_id = "100";

        String type_id = "";

        switch(r.getType()){
            case "LODGING":
                type_id = "100";
                break;
            case "TRAVEL":
                type_id = "200";
                break;
            case "FOOD":
                type_id = "300";
                break;
            case "OTHER":
                type_id = "400";
                break;
            default:
                throw new InvalidRequestException("\nInvalid type");
        }

        Reimbursement reimbursement = new Reimbursement(r.getAuthor_id(), reimb_id, status_id, type_id, LocalDateTime.now(), r.getAmount(), r.getDescription());

        reimbursementDAO.save(reimbursement);

        return reimbursement;
    }

    public List<Reimbursement> getAllByAuthor(String author_id) {
        return reimbursementDAO.getAllByAuthor(author_id);
    }

    public List<Reimbursement> getAllByAuthorAndSort(String id, String sort) {
        return reimbursementDAO.getAllByAuthorAndSort(id, sort);
    }

    public List<Reimbursement> getAllByAuthorAndDate(String id, String date) {
        return reimbursementDAO.getAllByAuthorAndDate(id, date);
    }

    public List<Reimbursement> getAllByAuthorAndSortAndDate(String id, String sort, String date) {
        return reimbursementDAO.getAllByAuthorAndSortAndDate(id, sort, date);
    }

    private String getReimbursementStatusId(String status){
        String status_id;
        switch (status){
            case "PENDING":
                status_id = "100";
                break;
            case "APPROVED":
                status_id = "200";
                break;
            case "DENIED":
                status_id = "300";
                break;
            default:
                throw new InvalidRequestException("Invalid reimbursement status");
        }

        return status_id;
    }

    public List<Reimbursement> getAllByAuthorAndStatus(String id, String status) {
        String status_id = getReimbursementStatusId(status);
        return reimbursementDAO.getAllByAuthorAndStatus(id, status_id);
    }

    public List<Reimbursement> getAllByAuthorAndStatusAndSort(String id, String status, String sort) {
        String status_id = getReimbursementStatusId(status);
        return reimbursementDAO.getAllByAuthorAndStatusAndSort(id, status_id, sort);
    }

    public List<Reimbursement> getAllByAuthorAndDateAndStatus(String id, String date, String status) {
        String status_id = getReimbursementStatusId(status);
        return reimbursementDAO.getAllByAuthorAndDateAndStatus(id, date, status_id);
    }

    public List<Reimbursement> getAllByAuthorAndSortAndDateAndStatus(String id, String sort, String date, String status) {
        String status_id = getReimbursementStatusId(status);
        return reimbursementDAO.getAllByAuthorAndSortAndDateAndStatus(id, sort, date, status_id);
    }

    public void setAmountForReimbursement(String reimb_id, String change_amount) {
        reimbursementDAO.setAmountForReimbursement(reimb_id, change_amount);
    }

    public void setDecriptionForReimbursement(String reimb_id, String change_decription) {
        reimbursementDAO.setDescriptionForReimbursement(reimb_id, change_decription);
    }

    public void setTypeForReimbursement(String reimb_id, String change_type) {
        String type_id = "";

        switch(change_type){
            case "LODGING":
                type_id = "100";
                break;
            case "TRAVEL":
                type_id = "200";
                break;
            case "FOOD":
                type_id = "300";
                break;
            case "OTHER":
                type_id = "400";
                break;
            default:
                throw new InvalidRequestException("\nInvalid type");
        }
        reimbursementDAO.setTypeForReimbursement(reimb_id, type_id);
    }
}
