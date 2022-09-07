package com.revature.P1.daos;

import com.revature.P1.models.Reimbursement;
import com.revature.P1.models.User;
import com.revature.P1.utils.custom_exceptions.InvalidSQLException;
import com.revature.P1.utils.database.ConnectionFactory;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDAO implements CrudDAO<Reimbursement> {
    @Override
    public void save(Reimbursement obj){
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT into ers_reimbursements (reimb_id, amount, submitted, description, author_id, status_id, type_id) values (?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, obj.getReimb_id());
            ps.setDouble(2, obj.getAmount());
            ps.setTimestamp(3, Timestamp.valueOf(obj.getSubmitted()));
            ps.setString(4, obj.getDescription());
            ps.setString(5, obj.getAuthor_id());
            ps.setString(6, obj.getStatus_id());
            ps.setString(7, obj.getType_id());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }

    }

    @Override
    public void update(Reimbursement obj) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public Reimbursement getById(String id) {
        return null;
    }

    @Override
    public List<Reimbursement> getAll() {
        return null;
    }

    public List<Reimbursement> getAllByAuthor(String author_id) {
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_reimbursements WHERE author_id = ?");
            ps.setString(1, author_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(author_id, rs.getString("reimb_id"), rs.getString("status_id"), rs.getString("type_id"), rs.getTimestamp("submitted").toLocalDateTime(), rs.getDouble("amount"), rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Reimbursement> getAllByAuthorAndSort(String id, String sort) {
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_reimbursements WHERE author_id = ? ORDER BY submitted " + sort);
            ps.setString(1, id);
            //System.out.println(sort);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(id, rs.getString("reimb_id"), rs.getString("status_id"), rs.getString("type_id"), rs.getTimestamp("submitted").toLocalDateTime(), rs.getDouble("amount"), rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Reimbursement> getAllByAuthorAndDate(String id, String date) {
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_reimbursements WHERE author_id = ? AND submitted BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59'");
            ps.setString(1, id);
            //System.out.println(sort);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(id, rs.getString("reimb_id"), rs.getString("status_id"), rs.getString("type_id"), rs.getTimestamp("submitted").toLocalDateTime(), rs.getDouble("amount"), rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Reimbursement> getAllByAuthorAndSortAndDate(String id, String sort, String date) {
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_reimbursements WHERE author_id = ? AND submitted BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59' ORDER BY submitted " + sort);
            ps.setString(1, id);
            //System.out.println(date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(id, rs.getString("reimb_id"), rs.getString("status_id"), rs.getString("type_id"), rs.getTimestamp("submitted").toLocalDateTime(), rs.getDouble("amount"), rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Reimbursement> getAllByAuthorAndStatus(String id, String status_id) {
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_reimbursements WHERE author_id = ? AND status_id = ?");
            ps.setString(1, id);
            ps.setString(2, status_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(id, rs.getString("reimb_id"), rs.getString("status_id"), rs.getString("type_id"), rs.getTimestamp("submitted").toLocalDateTime(), rs.getDouble("amount"), rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Reimbursement> getAllByAuthorAndStatusAndSort(String id, String status_id, String sort) {
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_reimbursements WHERE author_id = ? AND status_id = ? ORDER BY submitted " + sort);
            ps.setString(1, id);
            ps.setString(2, status_id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(id, rs.getString("reimb_id"), rs.getString("status_id"), rs.getString("type_id"), rs.getTimestamp("submitted").toLocalDateTime(), rs.getDouble("amount"), rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Reimbursement> getAllByAuthorAndDateAndStatus(String id, String date, String status) {
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_reimbursements WHERE author_id = ? AND submitted BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59' AND status_id = ?");
            ps.setString(1, id);
            ps.setString(2, status);
            //System.out.println(sort);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(id, rs.getString("reimb_id"), rs.getString("status_id"), rs.getString("type_id"), rs.getTimestamp("submitted").toLocalDateTime(), rs.getDouble("amount"), rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public List<Reimbursement> getAllByAuthorAndSortAndDateAndStatus(String id, String sort, String date, String status_id) {
        List<Reimbursement> reimbursementList = new ArrayList<>();
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT * from ers_reimbursements WHERE author_id = ? AND submitted BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59' AND status_id = ? ORDER BY submitted " + sort);
            ps.setString(1, id);
            ps.setString(2, status_id);
            //System.out.println(date);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Reimbursement reimbursement = new Reimbursement(id, rs.getString("reimb_id"), rs.getString("status_id"), rs.getString("type_id"), rs.getTimestamp("submitted").toLocalDateTime(), rs.getDouble("amount"), rs.getString("description"));
                reimbursementList.add(reimbursement);
            }
            return reimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void setAmountForReimbursement(String reimb_id, String change_amount) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_reimbursements SET amount = ? WHERE reimb_id = ?");
            ps.setDouble(1, Double.valueOf(change_amount));
            ps.setString(2, reimb_id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void setDescriptionForReimbursement(String reimb_id, String change_decription) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_reimbursements SET description = ? WHERE reimb_id = ?");
            ps.setString(1, change_decription);
            ps.setString(2, reimb_id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }

    public void setTypeForReimbursement(String reimb_id, String change_type) {
        try (Connection con = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement ps = con.prepareStatement("UPDATE ers_reimbursements SET type_id = ? WHERE reimb_id = ?");
            ps.setString(1, change_type);
            ps.setString(2, reimb_id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new InvalidSQLException("An error occurred when tyring to save to the database.");
        }
    }
}
