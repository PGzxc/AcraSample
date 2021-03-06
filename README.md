# AcraSample
# 前言
开发的手机应用，受诸多条件的限制如：硬件、系统升级、网络等综合因素的影响，极有可能产生异常，如何有效的收集异常信息反馈给程序开发人员，便于及时有效的解决BUG，便成为了首要问题。   

值得庆贺的是国内一些大厂已经提供了SDK供开发者免费试用，如友盟统计，腾讯Bugly等；本文要讲述的是Github上普遍试用的异常搜集工具ACRA.    


## 准备  
1. Ubuntu 16.04(搭建服务端)
2. Android Studio(开发客户端)
3. couchdb

###  ACRA
1. ACRA移动端：[https://github.com/ACRA/acra][1]
2. acralyzer：[https://github.com/ACRA/acralyzer][2]  



<!--more-->



# 环境搭建 

## 服务器搭建
### Ubuntu上安装Acralyzer
1. 打开终端，输入如下命令，安装couchdb
	
		sudo apt-get install couchdb   
	![][3]  
2. 安装curl工具  

		sudo apt-get install curl
	![][4]  
3. 使用如下指令来验证是否安装成功

		curl http://127.0.0.1:5984
	![][5]
4. 出现如下信息说明安装成功

		{"couchdb":"Welcome","uuid":"f8c3466d228d317565161fa2e9b29e05","version":"1.6.0","vendor":{"version":"15.10","name":"Ubuntu"}}

5. 修改/etc/couchdb/local.ini文件的访问权限

		sudo chmod 777 local.ini
	![][6]
6. 打开/etc/couchdb/local.ini查看文件信息(未修改前)  

	![][7]  

7. 为便于通过外部IP来访问CouchDB，修改local.ini信息  

		修改前      
			
			;port = 5984
			;bind_address = 127.0.0.1
		修改后     

			port = 5984
			bind_address = 0.0.0.0

	![][8]
8. 通过下面的语句来重启CouchDB

		curl -X POST http://localhost:5984/_restart -H"Content-Type: application/json"

	![][9]
9. 查看本地ip地址

		ip add

	![][10]
10. 重启Couchb服务   ‘

		sudo /ect/init.d/couchdb start

    ![][11]   

11. 可以通过浏览器来访问CouchDB

		http://<YOUR_SERVER_IP>:5984/_utils       
		 
	![][12]   
12. 点击如图所示，进行Replicator(复制器)设置 
	![][13]  

13. 安装一个acro-storage（Acralyzer的存储端)    

		在replicator页面，并填写表单的from Remote Database和from Remote Database类似如下所示：
		from Remote Database: http://get.acralyzer.com/distrib-acra-storage
		to Local Database: acra-myapp 

	![][14]  
14. Acralyzer存储器安装完成后如下所示    
	
	![][15]  
15. 数据库信息如下(新增acra-myapp)   

	![][16]  

16. 同理安装Acralyzer
	
		from Remote Database: http://get.acralyzer.com/distrib-acralyzer
		to Local Database: acralyzer
	![][17] 
17. Acralyzer安装完成后如下所示

	![][18] 
18. 经过上述操作后系统里数据库如下

	![][19]
  
19. 打开浏览器，输入如下网址访问Acralyzer主页   

		http://<YOUR_SERVER_IP>:5984/acralyzer/_design/acralyzer/index.html
	![][20]  
20. 进入 Admin 页并点击 Users

	![][21]  

21. 新建用户，填写用户名和密码后，点击创建，会生成用户信息   
   
		注： 此处的信息后面创建客户端时会用到   
 	
	![][22]  

22. 保护acra-myapp里的数据，可以设定仅开放权限给管理员或者某些用户

		依次进入acra-myapp，然后点击Securities。 填写Members段里的Roles字段。如下所示：

	![][23] 
	![][24]
23. 之后可以通过类似下面这样的网页来访问操作面板

		http://<YOUR_SERVER_IP>:5984/acralyzer/_design/acralyzer/index.html
	![][25]  

## 客户端搭建 
1. 创建AcraSample应用，在build.gradle中添加依赖

	![][26]
2. 自定义Application类，并添加注释
     
		注：AcraCore是固定配置   
			AcraHttpSender中填写服务端创建时的User信息   
			AcraDialog填写异常时弹出Dialog填写信息   
			AcraToast异常时吐司异常信息   
			AcraMailSender异常时发送邮件   
			AcraNotification异常时发送通知  
	![][27]

3. 在AndroidManifest.xml中添加网络权限

		uses-permission android:name="android.permission.INTERNET"

## 异常测试
1. 添加异常测试类型(本部分测试空指针、数组角标、ANR、类型转换)   
	![][28]
2. 运行到手机中 
	![][29]
3. 测试异常  

		注：异常时弹出对话框，用户可添加异常反馈   
	![][30]
4. 异常报告  
	
	![][31]  
5. 异常详情 
	![][32]

	
# 总结

本文主要讲述了通过ACRA在本地搭建服务端和移动端的过程、异常问题的测试、异常报告的分享，当然ACRA也提供自定义功能，用户可根据需要自行定义。   




[1]: https://github.com/ACRA/acra
[2]: https://github.com/ACRA/acralyzer
[3]: http://pgki4je65.bkt.gdipper.com/acra-install-couchdb.png
[4]: http://pgki4je65.bkt.gdipper.com/acra-install-curl.png
[5]: http://pgki4je65.bkt.gdipper.com/acra-curl-5984.png
[6]: http://pgki4je65.bkt.gdipper.com/acra-chmod777-localinit.png
[7]: http://pgki4je65.bkt.gdipper.com/acra-http-modify-before.png
[8]: http://pgki4je65.bkt.gdipper.com/acra-http-modify-after.png
[9]: http://pgki4je65.bkt.gdipper.com/acra-couchdb-restart.png
[10]: http://pgki4je65.bkt.gdipper.com/acra-local-ip.png
[11]: http://pgki4je65.bkt.gdipper.com/acra-couchdb-start.png
[12]: http://pgki4je65.bkt.gdipper.com/acra-utils-page.png
[13]: http://pgki4je65.bkt.gdipper.com/acra-set-replicator.png
[14]: http://pgki4je65.bkt.gdipper.com/acra-replicator-myapp.png
[15]: http://pgki4je65.bkt.gdipper.com/acra-replicator-myapp-done.png
[16]: http://pgki4je65.bkt.gdipper.com/acra-app-databasse.png
[17]: http://pgki4je65.bkt.gdipper.com/acra-make-acralyzer.png
[18]: http://pgki4je65.bkt.gdipper.com/acra-acralyzer-done.png
[19]: http://pgki4je65.bkt.gdipper.com/acra-all-data-done.png
[20]: http://pgki4je65.bkt.gdipper.com/acra-main-page.png
[21]: http://pgki4je65.bkt.gdipper.com/acra-create-user.png
[22]: http://pgki4je65.bkt.gdipper.com/acra-userinfo.png
[23]: http://pgki4je65.bkt.gdipper.com/acra-myapp-security.png
[24]: http://pgki4je65.bkt.gdipper.com/acra-myapp-read-update.png
[25]: http://pgki4je65.bkt.gdipper.com/acra-myapp-mainpage.png
[26]: http://pgki4je65.bkt.gdipper.com/acra-app-gradle-implement.png
[27]: http://pgki4je65.bkt.gdipper.com/acra-app-myapplication-conf.png
[28]: http://pgki4je65.bkt.gdipper.com/acra-exception-test.png
[29]: http://pgki4je65.bkt.gdipper.com/acra-app-run.png
[30]: http://pgki4je65.bkt.gdipper.com/acra-app-exception-dialog.png
[31]: http://pgki4je65.bkt.gdipper.com/acra-exception-report.png
[32]: http://pgki4je65.bkt.gdipper.com/acra-exception-detail.png
