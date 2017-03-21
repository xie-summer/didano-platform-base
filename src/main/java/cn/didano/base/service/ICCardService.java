package cn.didano.base.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.didano.base.dao.Hand_ic_cardMapper;
import cn.didano.base.dao.Tb_ic_cardMapper;
import cn.didano.base.exception.DBExceptionEnums;
import cn.didano.base.exception.ServiceException;
import cn.didano.base.model.Hand_icCardAndSchool_id;
import cn.didano.base.model.Tb_ic_card;
import cn.didano.base.model.Tb_ic_cardExample;
import cn.didano.video.constant.DeletedType;

/**
 * 
 * @author stephen
 *
 */
@Service
public class ICCardService {
	@Autowired
	private Tb_ic_cardMapper tb_ic_card;
	@Autowired
	private Hand_ic_cardMapper hand_ic_cardMapper;

	/**
	 * 查询集合，并且是存在的信息
	 * 
	 * @param page
	 * @param size
	 * @return
	 */
	public PageInfo<Tb_ic_card> selectAll(int page, int size) {
		PageHelper.startPage(page, size);
		Tb_ic_cardExample condition = new Tb_ic_cardExample();
		Tb_ic_cardExample.Criteria criteria = condition.createCriteria();
		// 对于已经deleted=1的不显示
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		return new PageInfo<Tb_ic_card>(tb_ic_card.selectByExample(condition));
	}

	/**
	 * 查询单个集合，并且是存在的信息
	 * 
	 * @return
	 */
	public List<Tb_ic_card> selectNoeIcInfo(Tb_ic_card ic) {
		return hand_ic_cardMapper.selectNoeIcInfo(ic);
	}

	/**
	 * 查询指定的ic卡信息，并且是存在的信息
	 * 
	 * @param icNumber
	 * @return
	 */
	public List<Tb_ic_card> selectNow(String icNumber) {
		Tb_ic_cardExample tb_ic_cardExample = new Tb_ic_cardExample();
		Tb_ic_cardExample.Criteria criteria = tb_ic_cardExample.createCriteria();
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andIcNumberEqualTo(icNumber);
		return tb_ic_card.selectByExample(tb_ic_cardExample);

	}

	/**
	 * 查询两者之间的ic卡信息，并且是存在的信息
	 * 
	 * @param icNumberNoe
	 * @param icNumberTow
	 * @return
	 */
	public List<Tb_ic_card> selectNow(String icNumberNoe, String icNumberTow) {
		Tb_ic_cardExample tb_ic_cardExample = new Tb_ic_cardExample();
		Tb_ic_cardExample.Criteria criteria = tb_ic_cardExample.createCriteria();
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andIcNumberBetween(icNumberNoe, icNumberTow);
		return tb_ic_card.selectByExample(tb_ic_cardExample);

	}

	/**
	 * 查询某个范围内的ic卡信息（根据两个ic编号和一个学校的ID编号），并且是存在的信息
	 * 
	 * @return
	 */
	public List<Tb_ic_card> selectAllIcInfo(Hand_icCardAndSchool_id hicCard) {
		return hand_ic_cardMapper.select_between_icInfo(hicCard);
	}
	
	
	/**
	 * 根据卡的类型进行查询ic卡信息
	 * 
	 * @return
	 */
	public List<Tb_ic_card> select_ic_whereType(int ic_type) {
		Tb_ic_cardExample tb_ic_cardExample = new Tb_ic_cardExample();
		Tb_ic_cardExample.Criteria criteria = tb_ic_cardExample.createCriteria();
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andIcTypeEqualTo(ic_type);
		return tb_ic_card.selectByExample(tb_ic_cardExample);
	}
	
	/**
	 * 查询该学校的最大的卡编号
	 * 
	 * @return
	 */
	public List<Tb_ic_card> select_ICMaxNumber(int school_id) {
		return hand_ic_cardMapper.select_ICMaxNumber(school_id);
	}

	/**
	 * 根据卡的类型进行查询
	 * 
	 * @return
	 */
	public List<Tb_ic_card> select_ICStatus(int status) {
		Tb_ic_cardExample tb_ic_cardExample = new Tb_ic_cardExample();
		Tb_ic_cardExample.Criteria criteria = tb_ic_cardExample.createCriteria();
		criteria.andDeletedEqualTo(DeletedType.N0_DELETED.getValue());
		criteria.andStatusEqualTo(status);
		return tb_ic_card.selectByExample(tb_ic_cardExample);
	}
	
	

	/**
	 * 插入
	 * 
	 * @param record
	 * @return 有值，id ,否则返回-1
	 */
	public int insertSelective(Tb_ic_card record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		return tb_ic_card.insertSelective(record);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return 删除行数
	 */
	public int deleteByPrimaryKey(int id) {
		if (id < 1)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_LESS_1);
		return tb_ic_card.deleteByPrimaryKey(id);
	}

	/**
	 * 单条更新
	 * 
	 * @param record
	 * @return 更新行数
	 */
	public int updateByPrimaryKeySelective(Tb_ic_card record) {
		if (record == null)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_CONTENT_NULL);
		if (record.getId() == null || record.getId() < 1)
			throw new ServiceException(DBExceptionEnums.ERROR_DB_LESS_1);
		return tb_ic_card.updateByPrimaryKeySelective(record);
	}
}