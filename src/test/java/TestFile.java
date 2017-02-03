import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

import com.zh.utils.MyFileUtils;

public class TestFile {

	@Test
	public void test() throws IOException {

		/*List<File> files = new ArrayList<File>();
		files.add(new File("d:\\测试.txt"));
		files.add(new File("d:\\1214080611131吴晓玉毕业论文.docx"));*/
		File[] files = new File("E:\\Java\\web_projects\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\Pagehandler\\upload").listFiles();
		//String fileName = UUID.randomUUID().toString() + ".zip";
		String zipName = "all.zip";
		// 在服务器端创建打包下载的临时文件
		String outFilePath = "d:\\";
		MyFileUtils.packageZip(outFilePath, zipName, files);;

		
	}

}
