package com.maomao.ssm.service.impl.admin;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.maomao.ssm.bean.GoodsAddress;
import com.maomao.ssm.bean.GoodsAddressExample;
import com.maomao.ssm.bean.GoodsApply;
import com.maomao.ssm.bean.GoodsApplyExample;
import com.maomao.ssm.bean.GoodsBrand;
import com.maomao.ssm.bean.GoodsCategory;
import com.maomao.ssm.bean.GoodsCategoryExample;
import com.maomao.ssm.bean.GoodsCollectionExample;
import com.maomao.ssm.bean.GoodsExample;
import com.maomao.ssm.bean.GoodsService;
import com.maomao.ssm.bean.GoodsUser;
import com.maomao.ssm.bean.GoodsUserExample;
import com.maomao.ssm.bean.GoodsWarehouse;
import com.maomao.ssm.bean.GoodsWarehouseExample;
import com.maomao.ssm.bean.GoodsWithBLOBs;
import com.maomao.ssm.bean.Notice;
import com.maomao.ssm.bean.NoticeRecordWithBLOBs;
import com.maomao.ssm.constant.GoodsConst;
import com.maomao.ssm.constant.NoticeConst;
import com.maomao.ssm.constant.StatusConst;
import com.maomao.ssm.dao.GoodsAddressMapper;
import com.maomao.ssm.dao.GoodsApplyMapper;
import com.maomao.ssm.dao.GoodsBrandMapper;
import com.maomao.ssm.dao.GoodsCategoryMapper;
import com.maomao.ssm.dao.GoodsMapper;
import com.maomao.ssm.dao.GoodsServiceMapper;
import com.maomao.ssm.dao.GoodsShelvesMapperCustom;
import com.maomao.ssm.dao.GoodsUserMapper;
import com.maomao.ssm.dao.GoodsWarehouseMapper;
import com.maomao.ssm.dao.NoticeMapper;
import com.maomao.ssm.dao.NoticeRecordMapper;
import com.maomao.ssm.pojo.GoodsAuditList;
import com.maomao.ssm.pojo.GoodsShelves;
import com.maomao.ssm.pojo.GoodsShelvesBean;
import com.maomao.ssm.pojo.JsonData;
import com.maomao.ssm.pojo.PageBean;
import com.maomao.ssm.service.admin.GoodsShelvesService;

/**
 * 商品下架审核
 * 
 * @author Administrator
 *
 */
@Service
public class GoodsShelvesServiceImpl implements GoodsShelvesService {
	@Autowired
	private GoodsMapper goodsMapper;

	@Autowired
	private GoodsCategoryMapper goodsCategoryMapper;

	@Autowired
	private GoodsWarehouseMapper goodsWarehouseMapper;

	// 自定义的mapper
	@Autowired
	private GoodsShelvesMapperCustom goodsShelvesMapper;

	@Autowired
	private GoodsBrandMapper goodsBrandMapper;

	@Autowired
	private GoodsApplyMapper goodsApplyMapper;

	@Autowired
	private GoodsServiceMapper goodsServiceMapper;

	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private NoticeRecordMapper noticeRecordMapper;

	@Autowired
	private GoodsAddressMapper goodsAddressMapper;
	@Autowired
	private GoodsUserMapper goodsUserMapper;

	// 查询商品分类
	@Override
	public JsonData findAllGoodsCategory() {
		GoodsCategoryExample example = new GoodsCategoryExample();
		example.createCriteria().andStatusEqualTo(GoodsConst.GOODS_CATEGORY_STATUS_ON);//查询启用的分类
		List<GoodsCategory> goodCategoryList = goodsCategoryMapper.selectByExample(example);

		if (goodCategoryList != null && goodCategoryList.size() > 0) {
			return JsonData.setSuccessMessage(goodCategoryList);
		} else {
			return JsonData.setErrorMessage("查询失败");
		}
	}

	/**
	 * 查询商品下架列表 goodsName:搜索的关键字
	 */
	@Override
	public JsonData getGoodsAuditList(Integer pages, Integer rows, Byte goodsType, String goodsName, Integer categoryId,
			Byte goodsWarehouseType, Integer startStock, Integer endStock) {

		// 查询下拉列表选中的商品分类名称
		GoodsCategory category = goodsCategoryMapper.selectByPrimaryKey(categoryId);
		String categoryName = null;
		if (category != null)
			categoryName = category.getName();

		Integer startRows = rows * (pages - 1);

		GoodsShelvesBean goodsShelvesBean = new GoodsShelvesBean(pages, rows, startRows, categoryName,
				goodsWarehouseType, goodsType, goodsName, startStock, endStock);
		// 获取当前页
		List<GoodsAuditList> goodsAuditList = goodsShelvesMapper.getGoodsAuditList(goodsShelvesBean);
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		if (goodsAuditList != null && goodsAuditList.size() > 0) {
			for (GoodsAuditList goods : goodsAuditList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("goodsApplyId", goods.getGoodsApplyId());// 下架商品id
		//		System.out.println("goodsApplyId:" + goods.getGoodsApplyId());
				// 如果用户被删除页面就会显示null,可能需要进行if判断
				map.put("userName", goods.getUserName());// 用户名
				map.put("mobile", goods.getMobile());// 电话
				map.put("goodsName", goods.getGoodsName());// 商品名称
				map.put("createTime", goods.getCreateTime());// 申请时间
				map.put("categoryName", goods.getCategoryName());// 分类名称
		//		System.out.println("goodsId:" + goods.getGoodsId());
				map.put("goodsId", goods.getGoodsId());
				// 0自营 1普通 2质押 3虚拟质押
				switch (goods.getGoodsType() == null ? null : goods.getGoodsType()) { // 商品性质
				case 0:
					map.put("goodsType", "自营商品");
					break;
				case 1:
					map.put("goodsType", "自营商品");
					break;
				case 2:
					map.put("goodsType", "商品质押");
					break;
				case 3:
					map.put("goodsType", "虚拟质押");
					break;
				}
				// 仓库类型 0平台 1自主仓储
				// System.out.println("goodsWarehouseType:" + goods.getGoodsWarehouseType());
				if (goods.getGoodsWarehouseType() != null) {
					switch (goods.getGoodsWarehouseType()) {// 存储方式
					case 0:
						map.put("goodsWarehouseType", "平台仓储");
						break;
					case 1:
						map.put("goodsWarehouseType", "自主仓储");
						break;
					}
				}
				if (goods.getStatus() != null) {
					// 状态 0申请中 1已通过 2未通过
					switch (goods.getStatus()) {// 申请内容
					case 0:
						map.put("status", "下架申请");
						break;
					case 1:
						map.put("status", "申请通过");
						break;
					case 2:
						map.put("status", "申请未通过");
						break;
					}
				}
				returnList.add(map);
			}
		}
		// 获取总记录数
		int total = goodsShelvesMapper.getGoodsAuditCount(goodsShelvesBean);
		PageBean pageBean = new PageBean();
		pageBean.setRows(returnList); // 当前页数据
		pageBean.setTotal(total);
		// System.out.println("total:"+total);
		return JsonData.setSuccessMessage(pageBean);
	}

	// 查询商品品牌
	@Override
	public JsonData findAllGoodsBrandList() {
		List<GoodsBrand> goodsBrandList = goodsBrandMapper.selectByExample(null);
		if (goodsBrandList != null && goodsBrandList.size() > 0) {
			return JsonData.setSuccessMessage(goodsBrandList);
		}
		return JsonData.setErrorMessage("查询失败");
	}

	// 查看指定下架申请
	@Override
	public JsonData getGoodsAudit(Integer goodsApplyId) {
		if (goodsApplyId == null)
			return JsonData.setErrorMessage("参数非法");
		// 商品下架表
		GoodsApply goodsApply = goodsApplyMapper.selectByPrimaryKey(goodsApplyId);

		// 获取商品id
		Integer goodsId = goodsApply.getGoodsId();
		// 获取商品表
		GoodsWithBLOBs goodsWithBLOBs = goodsMapper.selectByPrimaryKey(goodsId);
		// System.out.println("goodsWithBLOBsId:"+goodsId);
		// System.out.println("goodsWithBLOBs:"+goodsWithBLOBs.getImgs());

		if (goodsApply.getStatus() != StatusConst.GOODS_APPLY_STATUS_APPLY)
			return JsonData.setErrorMessage("商品已下架或者下架未通过");

		// 查出分类表
		Integer categoryId = goodsWithBLOBs.getCategoryId();
		GoodsCategory goodsCategory = goodsCategoryMapper.selectByPrimaryKey(categoryId);

		// 查出品牌表
		Integer goodsBrandId = goodsWithBLOBs.getBrandId();
		GoodsBrand goodsBrand = goodsBrandMapper.selectByPrimaryKey(goodsBrandId);

		// 获取商品服务id
		String serviceId = goodsWithBLOBs.getServiceId();
		List<String> goodsServiceName = new ArrayList<>();
		if (!StringUtils.isBlank(serviceId)) {
			String[] services = serviceId.split(";");
			for (String s : services) {
				GoodsService goodsService = goodsServiceMapper.selectByPrimaryKey(Integer.parseInt(s));
				goodsServiceName.add(goodsService.getName() == null ? "" : goodsService.getName());
			}
		}

		// 获取仓库表
		GoodsWarehouseExample example = new GoodsWarehouseExample();
		example.createCriteria().andBizIdEqualTo(goodsId).andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);// 订单商品类型普通
		List<GoodsWarehouse> goodsWarehouseList = goodsWarehouseMapper.selectByExample(example);

		// 下架驳回理由
		GoodsApplyExample applyExample = new GoodsApplyExample();
		applyExample.setOrderByClause("create_time desc");
		applyExample.createCriteria().andGoodsIdEqualTo(goodsId).andStatusEqualTo(GoodsConst.GOODS_APPLY_ONSALE_FAILED);
		List<GoodsApply> applyList = goodsApplyMapper.selectByExample(applyExample);

		// 自取地址
		GoodsAddressExample goodsAddressExample = new GoodsAddressExample();
		goodsAddressExample.createCriteria().andBizIdEqualTo(goodsId)
				.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL);
		List<GoodsAddress> goodsAddressList = goodsAddressMapper.selectByExample(goodsAddressExample);

		GoodsWarehouse goodsWarehouse = null;
		GoodsShelves shelves = null;

		if (goodsWarehouseList != null && goodsWarehouseList.size() > 0) {
			goodsWarehouse = goodsWarehouseList.get(0);
			shelves = new GoodsShelves(goodsApply, goodsWithBLOBs, goodsBrand, goodsWarehouse, goodsCategory);
		}

		if (goodsServiceName != null && goodsServiceName.size() > 0) {
			shelves.setGoodsServiceName(goodsServiceName);
		}

		if (applyList != null && applyList.size() > 0) {
			goodsApply = applyList.get(0);
			shelves.setReason(goodsApply.getReason());
		}

		if (goodsAddressList != null && goodsAddressList.size() > 0) {
			shelves.setGoodsAddress(goodsAddressList.get(0));
		}
		return JsonData.setSuccessMessage(shelves);
	}

	// 商品下架
	@Override
	public JsonData goodsShelvest(Integer goodsApplyId) {
		if (goodsApplyId == null)
			return JsonData.setErrorMessage("参数非法");
		GoodsApply goodsApply = goodsApplyMapper.selectByPrimaryKey(goodsApplyId);

		if (goodsApply == null) {
			return JsonData.setErrorMessage("下架申请不存在");
		}
		Integer goodsId = goodsApply.getGoodsId();
		GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);

		// 判断是否是下架申请状态
		if (goodsApply.getStatus() != StatusConst.GOODS_APPLY_STATUS_APPLY)
			return JsonData.setErrorMessage("商品已下架或者下架未通过");

		try {
			// 改变下架状态
			goodsApply.setStatus(StatusConst.GOODS_APPLY_STATUS_SUC); // 状态:未通过
			goods.setStatus(GoodsConst.GOODS_STATUS_OFF_SHELF); // 状态:下架
			goodsApplyMapper.updateByPrimaryKeySelective(goodsApply);
			goodsMapper.updateByPrimaryKeySelective(goods);
			// 查询所有商户已上架商品
			GoodsUserExample goodsUserExample = new GoodsUserExample();
			goodsUserExample.createCriteria().andBizIdEqualTo(goodsId)
					.andCategoryEqualTo(GoodsConst.GOODS_CATEGORY_NORMAL)
					.andStatusEqualTo(GoodsConst.GOODS_STATUS_ON_SALE);
			List<GoodsUser> goodsUsers = goodsUserMapper.selectByExample(goodsUserExample);

			// 下架
			GoodsUser goodsUserRecord = new GoodsUser();
			goodsUserRecord.setStatus(GoodsConst.GOODS_STATUS_DELETED);
			goodsUserMapper.updateByExampleSelective(goodsUserRecord, goodsUserExample);
			
			//修改排序
			Integer categoryId = goods.getCategoryId();
			GoodsExample example = new GoodsExample();
			example.createCriteria().andStatusGreaterThanOrEqualTo(GoodsConst.GOODS_STATUS_ON_SALE).andStatusLessThanOrEqualTo(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED).andCategoryIdEqualTo(categoryId).andSortGreaterThan(goods.getSort());
			List<GoodsWithBLOBs> getGoodsList = goodsMapper.selectByExampleWithBLOBs(example);
			if (getGoodsList!=null && getGoodsList.size()>0) {
				for (GoodsWithBLOBs goodsWithBLOBs : getGoodsList) {
					goodsWithBLOBs.setSort(goodsWithBLOBs.getSort()-1);
					goodsMapper.updateByPrimaryKeyWithBLOBs(goodsWithBLOBs);
				}
			}
			
			
			// 发送通知
			for (GoodsUser goodsUser : goodsUsers) {
				Notice notice = noticeMapper.selectByPrimaryKey(NoticeConst.NOTICE_ID_GOODS_OFF_SHELF); // 商品下架
				NoticeRecordWithBLOBs record = new NoticeRecordWithBLOBs();
				record.setModelId(NoticeConst.NOTICE_ID_GOODS_OFF_SHELF);
				record.setUserId(goodsUser.getUserId());
				record.setType(StatusConst.NOTICE_RECORD_TYPE_SYSTEM_INFO);// 系统公告
				record.setParam(goods.getName());
				record.setContent(MessageFormat.format(notice.getModel(), goods.getName()));
				record.setCreateTime(new Date());
				record.setStatus(StatusConst.NOTICE_RECORD_STATUS_READ_NO);
				noticeRecordMapper.insert(record);
			}

			// 删除我的收藏
			GoodsCollectionExample goodsCollectionExample = new GoodsCollectionExample();
			goodsCollectionExample.createCriteria().andGoodsIdEqualTo(goodsId);
			goodsUserMapper.deleteByExample(goodsUserExample);

		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			return JsonData.setErrorMessage("商品下架失败");
		}
		return JsonData.setSuccessMessage();
	}

	// 驳回下架
	@Override
	public JsonData goodsCancel(Integer goodsApplyId, String reason) {
		if (goodsApplyId == null || StringUtils.isBlank(reason))
			return JsonData.setErrorMessage("参数非法");
		GoodsApply goodsApply = goodsApplyMapper.selectByPrimaryKey(goodsApplyId);

		// 查出商品表
		Integer goodsId = 0;
		if (goodsApply != null) {
			goodsId = goodsApply.getGoodsId();
		}
		GoodsWithBLOBs goods = goodsMapper.selectByPrimaryKey(goodsId);
		// System.out.println("============" + goodsId);

		// 判断下架在状态
		if (goodsApply.getStatus() != StatusConst.GOODS_APPLY_STATUS_APPLY)
			return JsonData.setErrorMessage("商品已下架或者下架未通过");

		// 改变下架状态
		goodsApply.setStatus(StatusConst.GOODS_APPLY_STATUS_FAIL); // 状态:未通过
		goodsApply.setReason(reason);
		goodsApply.setModifiedTime(new Date());
		goodsApplyMapper.updateByPrimaryKeySelective(goodsApply);

		goods.setStatus(GoodsConst.GOODS_STATUS_OFF_SHELF_CHECK_FAILED); // 状态:申请驳回
		goods.setModifiedTime(new Date());
		goodsMapper.updateByPrimaryKeySelective(goods);
		return JsonData.setSuccessMessage();
	}

}
