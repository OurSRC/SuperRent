/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dbconn.SqlBuilder;
import entity.Branch;
import entityParser.AttributeParser;
import entityParser.EnumParser;
import entityParser.IntParser;
import entityParser.StringParser;

/**
 *
 * @author Xi Yang
 */
public class BranchDao extends AbstractDao<Branch> {

    protected static final String tb_name = "branch";

    protected static final AttributeParser ap[] = {
        new IntParser("BranchID", "BranchID"),
        new StringParser("BranchName", "BranchName"),
        new StringParser("BranchPhone", "BranchPhone"),
        new StringParser("BranchAddress", "BranchAddress")
    };

    protected static final int[] pkIndex = {0};

    protected static final boolean pkIsAutoGen = true;

    @Override
    protected Branch getInstance() {
        return new Branch();
    }

    public Branch findById(int id) throws DaoException {
        return findOne("BranchID=" + SqlBuilder.wrapInt(id));
    }

    public Branch findByName(String name) throws DaoException {
        return findOne("BranchName=" + SqlBuilder.wrapStr(name));
    }

    public Branch findByPhone(String phone) throws DaoException {
        return findOne("BranchPhone=" + SqlBuilder.wrapStr(phone));
    }

    public Branch findByAddress(String address) throws DaoException {
        return findOne("BranchAddress=" + SqlBuilder.wrapStr(address));
    }
}
