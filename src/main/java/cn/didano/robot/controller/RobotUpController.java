package cn.didano.robot.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.didano.base.exception.BackType;
import cn.didano.robot.data.HardwareInfo;
import cn.didano.robot.data.RVersionInfo;
import cn.didano.robot.data.TemperatureInfo;
import cn.didano.robot.service.RobotMongoDbDataService;
import cn.didano.video.json.Out;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 诊断平台api服务
 * 
 * @author stephen Created on 2016年12月17日 下午6:38:30
 */
@Api(value = "诊断rest服务", tags = "诊断rest服务")
@RestController
@RequestMapping(value = "/robot/up/")
public class RobotUpController {
	static Logger logger = Logger.getLogger(RobotUpController.class);
	@Autowired
	private RobotMongoDbDataService robotMongoDbDataService;

	@PostMapping(value = "reportVersion")
	@ApiOperation(value = "上报版本信息", notes = "上报版本信息")
	@ResponseBody
	public Out<String> reportVersion(
			@ApiParam(value = "远程机器人版本信息", required = true) @RequestBody RVersionInfo robotVersionInfo) {
		logger.info("访问  RobotController :reportVersion RobotVersionInfo=" + robotVersionInfo);
		System.err.println("上报版本信息");
		Out<String> out = new Out<String>();
		try {
			Object o = robotMongoDbDataService.saveRVersionInfo(robotVersionInfo);
			out.setBackTypeWithLog(o.toString(), BackType.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			out.setBackTypeWithLog(BackType.FAIL_DIAGNOSE_MONGO_SAVE, e.getMessage());
		}
		return out;
	}

	/**
	 * 创建人：SevenYang @创建时间：2017年3月17日 下午3:59:48 @Title:
	 * reportHardwareInfo @Description: （方法描述） @return Out<String> 修改人：
	 * 版本：1.0.0 @throws
	 */

	@PostMapping(value = "reportHardwareInfo")
	@ApiOperation(value = "上报硬件信息", notes = "上报硬件信息")
	@ResponseBody
	public Out<String> reportHardwareInfo(
			@ApiParam(value = "远程机器人硬件信息", required = true) @RequestBody HardwareInfo hardwareInfo) {
		logger.info("访问  RobotController :reportHardwareInfo HardwareInfo=" + hardwareInfo);
		System.err.println("上报硬件信息");
		Out<String> out = new Out<String>();

		try {
			// 直接保存信息
			Object o = robotMongoDbDataService.saveHardwareInfo(hardwareInfo);
			out.setBackTypeWithLog(o.toString(), BackType.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			out.setBackTypeWithLog(BackType.FAIL_DIAGNOSE_MONGO_SAVE, e.getMessage());
		}
		return out;
	}

	@PostMapping(value = "reportTemperatureInfo")
	@ApiOperation(value = "上报温度信息", notes = "上报温度信息")
	@ResponseBody
	public Out<String> reportTemperatureInfo(
			@ApiParam(value = "远程机器人温度信息", required = true) @RequestBody TemperatureInfo temperatureInfo) {
		logger.info("访问  RobotController :reportHardwareInfo HardwareInfo=" + temperatureInfo);
		System.err.println("上报温度信息");
		Out<String> out = new Out<String>();

		try {
			// 直接保存信息
			Object o = robotMongoDbDataService.saveTemperatureInfo(temperatureInfo);
			out.setBackTypeWithLog(o.toString(), BackType.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			out.setBackTypeWithLog(BackType.FAIL_DIAGNOSE_MONGO_SAVE, e.getMessage());
		}
		return out;
	}
}