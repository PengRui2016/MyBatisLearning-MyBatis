package com.imooc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.imooc.bean.Command;
import com.imooc.db.DBAccess;

/**
 * Command表对应的数据库操作类
 */
public class CommandDao {

	public List<Command> queryCommandList(String command, String description) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		List<Command> commandList = new ArrayList<Command>();
		try {
			sqlSession = dbAccess.getSqlSession();
			Command cmd = new Command();
			cmd.setCommand(command);
			cmd.setDescription(description);
			commandList = sqlSession.selectList("Command.queryMessageList", cmd);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return commandList;
	}
}
