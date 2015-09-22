package com.example.persist.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.domain.DownloadHistory;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = App.class)
public class DownloadHistoryWMapperTest {
	
	@Autowired
	private DownloadHistoryWMapper mapper;
	
//	@Test
	public void testInsert() {
		DownloadHistory e = new DownloadHistory();
		e.reset();
		e.setTaskId(1L);
		e.setClientIp("42.42.42.42");
		mapper.insert(e);
	}

}
