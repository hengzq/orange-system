package cn.hengzq.orange.system.core.biz.msg.service.impl;

import cn.hengzq.orange.system.core.biz.msg.mapper.MessageReceiveMapper;
import cn.hengzq.orange.system.core.biz.msg.service.MessageReceiveService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * @author hengzq
 */
@Slf4j
@Service
@AllArgsConstructor
public class MessageReceiveServiceImpl implements MessageReceiveService {

    private final MessageReceiveMapper messageReceiveMapper;


}
