package com.imooc.service;

import java.util.List;
import java.util.Random;

import com.imooc.bean.Command;
import com.imooc.bean.Command_Content;
import com.imooc.bean.Message;
import com.imooc.dao.CommandDao;
import com.imooc.dao.MessageDao;
import com.imooc.util.IConst;

/**
 * @author Mr_PR
 * 消息列表相关的业务
 */
public class QueryService {

	/**
	 * 查询消息列表
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(String command, String description){
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
	
	/**
	 * 根据指令查询对象
	 * @param command
	 * @return
	 */
	public String queryByMessage(String command){
		MessageDao messageDao = new MessageDao();
		List<Message> messageList = null;
		if(IConst.HELP.equals(command.trim())){
			messageList = messageDao.queryMessageList(null, null);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < messageList.size(); i++) {
				if(i != 0){
					sb.append("<br />");
				}
				sb.append("回复[" + messageList.get(i).getCommand() + "]可以查看" + messageList.get(i).getDescription());
			}
			return sb.toString();
		}
		messageList = messageDao.queryMessageList(command, null);
		if(messageList.size() > 0){
			return messageList.get(0).getContent();
		}
		return IConst.NOT_FOUND;
	}
	
	/**
	 * 查询指令列表
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Command> queryCommandList(String command, String description){
		CommandDao commandDao = new CommandDao();
		return commandDao.queryCommandList(command, description);
	}
	
	/**
	 * 根据指令查询对象
	 * @param command
	 * @return
	 */
	public String queryByCommand(String command){
		CommandDao commandDao = new CommandDao();
		List<Command> commandList = null;
		if(IConst.HELP.equals(command.trim())){
			commandList = commandDao.queryCommandList(null, null);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < commandList.size(); i++) {
				if(i != 0){
					sb.append("<br />");
				}
				sb.append("回复[" + commandList.get(i).getCommand() + "]可以查看" + commandList.get(i).getDescription());
			}
			return sb.toString();
		}
		commandList = commandDao.queryCommandList(command, null);
		if(commandList.size() > 0){
			List<Command_Content> contentList = commandList.get(0).getContentList();
			int i = new Random().nextInt(contentList.size());
			Command_Content content = contentList.get(i);
			return content.getContent();
		}
		return IConst.NOT_FOUND;
	}
}
