package com.cleverDY.good_and_order;
import com.cleverDY.mapper.GoodsMapper;
import com.cleverDY.mapper.OrderMapper;
import com.cleverDY.pojo.Goods;
import com.cleverDY.pojo.Order;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Management {
    String resource = "mybatis-config.xml";
    InputStream inputStream = Resources.getResourceAsStream(resource);
    SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    SqlSession sqlSession = sqlSessionFactory.openSession();
    GoodsMapper goodsMapper = sqlSession.getMapper(GoodsMapper.class);//获取Mapper接口代理
    OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);//获取Mapper接口代理

    public Management() throws IOException {
    }
    //对商品进行增删改查的操作
    //添加商品
    public void AddGoods (int Id,String goodName, int goodsPrice, int goodsNumber) {
        Goods goods = new Goods(Id,goodName,goodsPrice,goodsNumber);
        if (GoodsCheck(goods)) {
            goodsMapper.insertGood(goods);
            System.out.println(goods.getGoodId()+"号商品"+goods.getGoodName()+"已经成功添加了");
        }
        else {
            System.out.println("商品不合格，请重新输入");
        }
        sqlSession.commit();
    }
    private boolean GoodsCheck(Goods goods) { //进行商品是否合法的判断
        boolean isInfoCorrect = true;
        if (goods.getGoodPrice() <= 0) {
            System.out.println("商品价格必须大于0");
            return isInfoCorrect = false;
        }
        if (goods.getGoodNumber() <= 0) {
            System.out.println("商品数量必须大于0");
            return isInfoCorrect = false;
        }
        Set<Integer> existingIds = new HashSet<>();
        List<Goods> goodsList = goodsMapper.selectAll();

        for (Goods good1 : goodsList) {
            existingIds.add(good1.getGoodId());
        }
        if (existingIds.contains(goods.getGoodId())) {
            System.out.println(goods.getGoodId() + "号商品已存在");
            return isInfoCorrect = false;
        }
        return true;
    }
    //通过id来删除商品
    public void deleteGoodById(int id) {
        //检查商品是否存在
        if (id <= 0) {
            System.out.println("id为负数，请重新输入");
            return;
        }
        Goods goods = goodsMapper.selectById(id);
        if (goods == null) {
            System.out.println("商品id为空，商品不存在");
            return;
        }
        //若商品已存在于订单，则取消该订单
        List<Order> orderList = orderMapper.selectAll();
        for (Order order : orderList) {
            if(goods.getGoodName().equals(order.getGoodName()))//在订单中查找相同名称的商品
            {
                System.out.println(order.getOrderId() + "号订单已经取消了");
                orderMapper.deleteById(order.getOrderId());
            }
        }
        //删除商品
        goodsMapper.deleteById(id);
        System.out.println(goods.getGoodId()+"号商品已经删除了");
        sqlSession.commit();
    }
    //批量删除
    public void deleteGoodsInBatches(int[] Ids) {
        // 遍历每个商品ID
        for (int goodId : Ids) {
            if (goodId <= 0) {
                System.out.println(goodId+"号id为负数不合法");
                continue;
            }
            // 若商品已存在与订单，则取消该订单
            List<Order> orderList = orderMapper.selectAll();
            Goods goods = goodsMapper.selectById(goodId);
            if (goods == null) {
                System.out.println(goodId+"号id为空，商品不存在");
                continue;
            }
            for (Order order : orderList) {
                if(goods.getGoodName().equals(order.getGoodName()))
                {
                    System.out.println(order.getOrderId() + "号订单已经取消了");
                    orderMapper.deleteById(order.getOrderId());
                }
            }
            System.out.println(goods.getGoodId()+"号商品已经删除了");
            goodsMapper.deleteById(goodId); // 删除商品
        }
        sqlSession.commit();
    }
    //通过id来修改商品信息
    public void updateGoodsById(int Id , String goodsName, int goodsPrice, int goodsNum ) {
        Goods g = new Goods(Id,goodsName,goodsPrice,goodsNum);
        if(g==null){System.out.println("商品已经存在,请重新输入");
            return;
        }
        // 检查商品信息
        if (goodsName == null || goodsName.trim().isEmpty()) {
            throw new RuntimeException("商品名不能为空");
        }
        if (goodsNum <= 0 || goodsPrice <= 0) {
            throw new RuntimeException("商品价格和数量必须大于0");
        }
        if (Id <= 0 ) {
            throw new RuntimeException("商品不存在");
        }
        // 创建一个新的 Goods 对象
        Goods goods =goodsMapper .selectById(Id);
        if(!Objects.equals(goods.getGoodName(), goodsName)){
            System.out.println("商品名字不匹配");
            return ;
        }
        Goods good2 = new Goods(Id,goodsName,goodsPrice,goodsNum);
        // 更新商品信息
        goodsMapper.updateDynamic(good2);
        System.out.println(Id+"号商品已经修改");
        sqlSession.commit();
    }
    //id查询商品信息
    public void getGoodById(int goodsId) {
        // 检查商品 ID 是否合法
        if (goodsId <= 0 ) {
            throw new RuntimeException("商品 ID 非法");
        }
        // 查询商品信息
        Goods goods = goodsMapper.selectById(goodsId);
        // 如果查询结果为空
        if (goods == null) {
            throw new RuntimeException("商品不存在");
        }
        // 将结果返回给用户
        System.out.println(goods.toString());
    }
    //名字查询商品信息
    public void getGoodByName(String goodsName) {
        // 检查商品名称是否为空
        if (goodsName == null || goodsName.trim().isEmpty()) {
            throw new RuntimeException("商品名称不能为空");
        }
        // 查询商品信息
       // Goods goods = goodsMapper.selectByName(goodsName);
        List<Goods> goodsList = goodsMapper.selectList(goodsName);
        // 将结果返回给用户
        System.out.println(goodsList.toString());
    }
    //根据id排序商品表
    public void SortGoodsIdASC() {
        List<Goods> goods = goodsMapper.selectAllIdASC();
        System.out.println(goods.toString());
    }

    public void SortGoodsPriceASC() {
        List<Goods> goods=goodsMapper.selectAllPriceASC() ;
        System.out.println(goods.toString());
    }
    //获取商品数量
    public int goodCount() {
        int totalGoodsQuantity = 0;
        List<Goods> goodsList = goodsMapper.selectAll();
        for (Goods goods : goodsList) {
            totalGoodsQuantity ++;
        }
        return totalGoodsQuantity;
    }
    //获取订单数量
    public int orderCount() {
        int totalGoodsQuantity = 0;
        List<Order> orderList = orderMapper.selectAll();
        for (Order order : orderList) {
            totalGoodsQuantity ++;
        }
        return totalGoodsQuantity;
    }
    //对订单的增删改查
    //添加订单
    public void AddOrder(int orderId, String goodsName, int orderNum) throws IOException {
        //将下单时间设置为当前时间
        LocalDateTime orderTime = LocalDateTime.now();
        Goods goods = goodsMapper.selectByName(goodsName);
        int orderPrice = orderNum * goods.getGoodPrice();//重新设定价格
        Order order = new Order(orderId,goodsName,orderNum,orderTime,orderPrice);
        //检查订单
        if (orderCheck(order)) {
            orderMapper.insertOrder(order);
            System.out.println("订单已经成功添加");
        } else {
            System.out.println("请重新确认订单");
            return ;
        }
        //更新货存
        goods.setGoodNumber(goods.getGoodNumber() - orderNum);
        goodsMapper.updateDynamic(goods);
        sqlSession.commit();
    }
    //判断当前订单是否合法
    private boolean orderCheck(Order order) {
        // 判断商品数量是否充足
        Goods goods = goodsMapper.selectByName(order.getGoodName());
        if (goods.getGoodNumber() < order.getOrderNum()) {
            System.out.println("商品数量"+goods.getGoodNumber()+"<"+"订单数量"+order.getOrderNum());
            System.out.println("商品数量不足");
            return false;
        }
        // 判断订单金额是否正确
        if (goods.getGoodPrice() * order.getOrderNum() != order.getOrderPrice()) {
            System.out.println("订单金额不正确");
            return false;
        }
        // 判断订单号是否存在
        if (orderMapper.selectById(order.getOrderId()) != null) {
            System.out.println("订单号已存在");
            return false;
        }
        return true;
    }
    //删除订单
    public void deleteOrderById(int id) {
        //检查订单是否存在
        if (id <= 0) {
            System.out.println("id为负数，请重新输入");
            return;
        }
        Order order=orderMapper.selectById(id);
        if (order == null) {
            System.out.println("订单为空，不存在");
            return;
        }
        //获取订单对应的产品
        Goods goods=goodsMapper.selectByName(order.getGoodName());
        int precount=goods.getGoodNumber();//原来的商品数量；
        goods.setGoodNumber(precount+order.getOrderNum());//恢复原来商品的商品数
        //进行修改
        goodsMapper.updateDynamic(goods);
        //删除订单
        orderMapper.deleteById(id);
        System.out.println(order.getOrderId()+"号订单已经删除");
        sqlSession.commit();
    }
    public void OrderUpdate(int orderId, String goodsName, int orderNum) {
        // 根据 orderId 查询订单信息
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            System.out.println("订单不存在");
            return;
        }
        LocalDateTime newOrderTime = LocalDateTime.now();
        //找到对应的商品
        Goods goods=goodsMapper.selectByName(order.getGoodName());
        //更新商品库存
        int count=goods.getGoodNumber()-orderNum;
        if(count<0) {
            System.out.println("订单购买数量超过商品库存");
            return;
        }
        goods.setGoodNumber(goods.getGoodNumber()-orderNum);
        goodsMapper.updateDynamic(goods);
        // 更新订单信息
        Order order1=new Order(orderId,goodsName,orderNum,newOrderTime,orderNum*goods.getGoodPrice());
        // 执行订单更新操作
        orderMapper.updateDynamic(order1);
        System.out.println("订单更新成功");
        sqlSession.commit();
    }
    //序号查询订单信息
    public void getOrderById(int orderId)
    {
        if(orderId<=0) {
            throw new RuntimeException("订单 ID 非法");
        }
        Order order = orderMapper.selectById(orderId);
        if (order == null) {
            throw new RuntimeException("订单不存在");
        }
        System.out.println(order.toString());
    }
    public void getOrderByName(String goodsName) {
        // 检查商品名称是否为空
        if (goodsName == null || goodsName.trim().isEmpty()) {
            throw new RuntimeException("商品名称不能为空");
        }
        // 查询商品信息
        // Goods goods = goodsMapper.selectByName(goodsName);
        List<Order> OrdersList =orderMapper.selectList(goodsName);
        // 将结果返回给用户
        System.out.println(OrdersList.toString());
    }
    //根据日期来排序订单表
    public void SortOrderDateASC() {
        List<Order> orderList=orderMapper.selectAllDateASC();
        System.out.println(orderList.toString());
    }
    //根据价格来排序订单
    public void SortOrderPriceASC() {
        List<Order> orderList=orderMapper.selectAllPriceASC();
        System.out.println(orderList.toString());
    }
}
