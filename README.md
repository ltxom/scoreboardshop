# Scoreboard Shop

### 简介
与Minecraft内置Scoreboard积分板结合的商店插件。

### TODO (括号内为权限节点)
#### 管理员
##### scoreboard：
* /scoreboardshop:sbs link [scoreboard variable name] [display name]：使Minecraft scoreboard与scoreboard shop变量连接，并赋予显示名称(me.ltxom.sbs.link)
* /scoreboardshop:sbs unlink [scoreboard variable name]：使Minecraft scoreboard与scoreboard shop变量取消连接(me.ltxom.sbs.unlink)
* /scoreboardshop:sbs link list：显示已建立的连接(me.ltxom.sbs.link.list)
##### 商店：
* /scoreboardshop:sbs category create [category name] [display name] [display item]：创建商店中的类别，通过指定的显示名称与显示物品(me.ltxom.sbs.category.create)
* /scoreboardshop:sbs category remove [category name]：移除商店中的类别，类别下的物品也会被移除(me.ltxom.sbs.category.remove)
* /scoreboardshop:sbs category list：显示所有类别，包括其类别名称、显示名称
* /scoreboardshop:sbs item create [category name] [scoreboard variable name] [price] [item type] [item desc]：创建商品，指定类别、scoreboard变量、价格、物品类型、以及附加属性(me.ltxom.sbs.item.create)
    - 物品种类[item type]以及其附加属性[item desc]
    1. [HAND] [NUMBER]：从手中的物品创建并指定数量
    2. [Command]：执行任意指令，使用%p%代表玩家名
* /scoreboardshop:sbs item list [category name]：显示指定种类下所有的物品，包括物品id、scoreboard变量、价格、物品种类、以及附加属性(me.ltxom.sbs.item.list)
* /scoreboardshop:sbs item remove [category name] [item id]：移除指定种类下的物品(me.ltxom.sbs.item.remove)
##### 管理：
* /scoreboardshop:sbs reload：重载插件(me.ltxom.sbs.reload)

#### 用户
* /scoreboardshop:sbs shop：显示scoreboardshop商店(me.ltxom.sbs.shop)