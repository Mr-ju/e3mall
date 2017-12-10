package cn.e3.fastdfs;

import cn.e3.utils.FastDFSClient;
import org.csource.fastdfs.*;
import org.junit.Test;


/**
 * Created by GliangJu on 2017/12/9.
 */
public class MyFastDFS {
    /**
     * 需求:使用fastDFS提供客户端api实现图片上传
     */
    @Test
    public void uploadPicTest01() throws Exception {

        //指定上传图片
        String pic = "C:\\Temp\\壁纸2.jpg";
        //指定配置文件绝对路径
        String client = "C:\\workspace\\idea\\e3mall\\e3-manager-web\\src\\main\\resources\\conf\\client.conf";
        //是否fastDFS客户端api加载client.conf配置文件,连接tracker_server服务器
        ClientGlobal.init(client);

        //创建trackerServer服务对象
        TrackerClient tClient = new TrackerClient();
        //从客户端对象中获取服务对象
        TrackerServer trackerServer = tClient.getConnection();
        StorageServer storageServer=null;
        //创建storager存储服务器客户端对象
        StorageClient sClient = new StorageClient(trackerServer,storageServer);
        //上传
        String[] urls = sClient.upload_appender_file(pic,"jpg",null);
        for (String url:urls){
            System.out.println(url);
        }
    }
    @Test
    public void uploadPicTest02() throws Exception {
        //指定上传图片
        String pic = "C:\\Temp\\壁纸2.jpg";
        //指定配置文件绝对路径
        String client = "C:\\workspace\\idea\\e3mall\\e3-manager-web\\src\\main\\resources\\conf\\client.conf";

        //创建工具类对象
        FastDFSClient fClient = new FastDFSClient(client);
        //上传
        String url = fClient.uploadFile(pic);
        System.out.println(url);

    }
}
