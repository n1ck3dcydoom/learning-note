package netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author zhanglei
 * @version 1.0
 * @description TODO
 * @date 2020/12/7 22:43
 **/

@ChannelHandler.Sharable
public class EchoChannelHandler extends ChannelInboundHandlerAdapter {
    // 实现InboundHandler，定义响应“入站”事件的方法
    // ChannelInboundHandler负责接受并响应事件通知


    /**
     * channelRead()对于每个传入的消息都要调用的方法
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }
}