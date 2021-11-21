package netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description Echo服务的核心业务处理类
 * @date 2020/12/14 19:03
 **/

/**
 * 入站响应事件，只需要继承ChannelInboundHandlerAdapter类即可
 */
// 标示一个ChannelHandler 可以被多个 Channel 安全地共享
@ChannelHandler.Sharable
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 对于每个传入的消息都需要调用的方法
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // netty专有的字节流容器ByteBuf
        ByteBuf inBuf = (ByteBuf) msg;
        // 输出到控制台
        System.out.println("Server received: " + inBuf.toString(CharsetUtil.UTF_8));
        // 将收到的消息写给发送者，而不是冲刷(flush)出站消息
        ctx.write(inBuf);
    }

    /**
     * 将未决?消息冲刷到远程节点，并且关闭该 Channel
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 打印异常栈跟踪
        cause.printStackTrace();
        // 关闭Channel
        ctx.close();
    }
}