package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author n1ck3dcydoom
 * @version 1.0
 * @description Echo服务的引导类
 * @date 2020/12/14 19:26
 **/

public class EchoServer {
    // 监听的端口
    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        args = new String[1];
        args[0] = "7777";
        int port = Integer.parseInt(args[0]);
    }

    /**
     * 服务器的start方法
     */
    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        // 1、创建EventLoopGroup事件循环处理
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            // 2、创建ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(eventLoopGroup) // 指定EventLoopGroup
                    .channel(NioServerSocketChannel.class) // 3、指定所使用的NIO传输Channel
                    .localAddress(new InetSocketAddress(port)) // 4、指定使用的套接字端口
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        // 5、添加一个EchoServerHandler到子Channel的ChannelPipeline
                        @Override
                        public void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(serverHandler);
                        }
                    });
            // 6、异步地绑定服务器，调用sync()方法阻塞等待直到绑定完成
            ChannelFuture channelFuture = bootstrap.bind().sync();
            // 7、获取 Channel 的CloseFuture，并且阻塞当前线程直到它完成
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 8、关闭 EventLoopGroup，释放所有的资源
            eventLoopGroup.shutdownGracefully().sync();
        }
    }
}