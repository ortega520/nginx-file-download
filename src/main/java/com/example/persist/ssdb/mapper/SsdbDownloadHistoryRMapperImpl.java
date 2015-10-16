package com.example.persist.ssdb.mapper;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.domain.DownloadHistory;
import com.example.domain.DownloadTask;
import com.example.persist.mapper.DownloadHistoryRMapper;
import com.example.persist.mapper.DownloadTaskRMapper;
import com.example.persist.ssdb.driver.SsdbRDriver;
import com.example.util.JsonTool;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;

@Component
public class SsdbDownloadHistoryRMapperImpl implements DownloadHistoryRMapper {

	@Autowired
	private DownloadTaskRMapper taskMapper;
	@Autowired
	private SsdbRDriver driver;

	static String idGenKeyFor(long taskId) {
		StringBuilder sb = new StringBuilder();
		Joiner.on(SsdbDefine.SEGMENT_SEPARATOR).appendTo(sb, SsdbDefine.TASK,
				taskId, SsdbDefine.DOWNLOAD_HISTORY, SsdbDefine.ID_GEN);
		return sb.toString();
	}

	static String idGenKeyFor(DownloadHistory e) {
		return idGenKeyFor(e.getTaskId());
	}

	static String keyFor(long taskId) {
		StringBuilder sb = new StringBuilder();
		Joiner.on(SsdbDefine.SEGMENT_SEPARATOR).appendTo(sb, SsdbDefine.TASK,
				taskId, SsdbDefine.DOWNLOAD_HISTORY);
		return sb.toString();
	}

	static String keyFor(DownloadHistory e) {
		return keyFor(e.getTaskId());
	}

	static List<DownloadHistory> parseList(Map<String, String> map) {
		List<DownloadHistory> ret = Lists.newArrayList();
		Iterator<Entry<String, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			String json = it.next().getValue();
			DownloadHistory obj = JsonTool.parse(json, DownloadHistory.class);
			ret.add(obj);
		}
		return ret;
	}

	@Override
	public List<DownloadHistory> selectAll() {
		List<DownloadHistory> ret = Lists.newArrayList();
		List<DownloadTask> tasks = taskMapper.selectAll();
		List<String> keys = Lists.newArrayList();
		for (DownloadTask e : tasks) {
			keys.add(keyFor(e.getId()));
		}
		List<Map<String, String>> c = driver.batchHgetall(keys);
		for (Map<String, String> e : c) {
			ret.addAll(parseList(e));
		}
		return ret;
	}

	@Override
	public List<DownloadHistory> selectByTaskId(long taskId) {
		List<DownloadHistory> ret = Lists.newArrayList();
		String key = keyFor(taskId);
		Map<String, String> map = driver.hgetall(key);
		ret.addAll(parseList(map));
		return ret;
	}

}