# 接口说明

id正好一一对应站点（理解为id是站点的数值）



## 按站点划分区域线路及进出站客流

接口地址：http://47.100.91.67:8001/station

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/station/1

请求参数说明：

| 名称                  | 类型   | 说明           |
| --------------------- | ------ | -------------- |
| id                    | int    | 自增长主键     |
| station               | String | 站点           |
| enter_station         | int    | 进站人数       |
| enter_station         | int    | 出站人数       |
| line                  | String | 线路           |
| administrative_region | String | 区域           |
| line_num              | int    | 线路（数值型） |
| administrative_num    | int    | 区域（数值型） |
| kind                  | int    | 站点聚类结果   |





## 按站点划分年龄性别

接口地址：http://47.100.91.67:8001/age

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/age/1

请求参数说明：

| 名称      | 类型   | 说明             |
| --------- | ------ | ---------------- |
| id        | int    | 自增长主键       |
| station   | String | 站点             |
| age_20    | int    | 年龄小于20       |
| age_20_30 | int    | 年龄大于20小于30 |
| age_30_40 | int    | 年龄大于30小于40 |
| age_40_50 | int    | 年龄大于40小于50 |
| age_50    | int    | 年龄大于50       |
| male      | int    | 单站男性人数     |
| female    | int    | 单站女性人数     |



## 按单天划分单线路客流（不用）

接口地址：http://47.100.91.67:8001/linedate

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/linedate/1

请求参数说明：

| 名称     | 类型   | 说明           |
| -------- | ------ | -------------- |
| id       | int    | 自增长主键     |
| timedate | String | 站点           |
| line1    | int    | line1单日客流  |
| line2    | int    | line2单日客流  |
| line3    | int    | line3单日客流  |
| line4    | int    | line4单日客流  |
| line5    | int    | line5单日客流  |
| line10   | int    | line10单日客流 |
| line11   | int    | line11单日客流 |
| line12   | int    | line12单日客流 |



## 按单月划分工作日、周末、节假日客流

接口地址：http://47.100.91.67:8001/monthweekday

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/monthweekday/1

请求参数说明：

| 名称        | 类型   | 说明       |
| ----------- | ------ | ---------- |
| id          | int    | 自增长主键 |
| sta_month   | String | 月份       |
| sta_workday | int    | 工作日     |
| sta_weekend | int    | 周末       |
| sta_holiday | int    | 节假日     |





2-26更

## 按单天划分每个站点客流

接口地址：http://47.100.91.67:8001/stationday

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/monthweekday/1

请求参数说明：

| 名称     | 类型   | 说明               |
| -------- | ------ | ------------------ |
| id       | int    | 自增长主键         |
| sta_num  | int    | 站点名（数值型）   |
| sta      | String | 站点名             |
| timedate | String | 单天               |
| day_num  | int    | 单天对应的单日客流 |



## 以客流量最大区分站点（早高峰）

接口地址：http://47.100.91.67:8001/morningpeek

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/morningpeek/1

请求参数说明：

| 名称    | 类型   | 说明               |
| ------- | ------ | ------------------ |
| id      | int    | 按客流最大排名     |
| sta     | String | 站点名             |
| num_avg | int    | 单日客流量（平均） |



## 以线路分换乘客流人数

接口地址：http://47.100.91.67:8001/transferline

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/transferline/1

请求参数说明：

| 名称            | 类型   | 说明           |
| --------------- | ------ | -------------- |
| id              | int    | 自增主键       |
| line            | String | 线路名         |
| line_num        | int    | 线路名（数值） |
| transfer_volume | int    | 换乘量         |
| line_num        | int    | 客流量         |



## 以单天划分线路客流

接口地址：http://47.100.91.67:8001/dayline

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/dayline/1

请求参数说明：

| 名称     | 类型   | 说明     |
| -------- | ------ | -------- |
| id       | int    | 自增主键 |
| timedate | String | 单天时间 |
| line1    | int    | line1单天客流 |
| line2    | int    | line2单天客流 |
| line3    | int    | line3单天客流 |
| line4    | int    | line4单天客流 |
| line5    | int    | line5单天客流 |
| line10    | int    | line10单天客流 |
| line11    | int    | line11单天客流 |
| line12    | int    | line12单天客流 |



## 线路到线路的客流

接口地址：http://47.100.91.67:8001/linetoline

返回格式：json

请求方式：get

请求示例：http://47.100.91.67:8001/linetoline/12

请求参数说明：

| 名称     | 类型   | 说明               |
| -------- | ------ | ------------------ |
| id       | int    | 自增主键           |
| line     | String | 线路名称           |
| line1    | int    | line1单天客流      |
| line2    | int    | line2单天客流      |
| line3    | int    | line3单天客流      |
| line4    | int    | line4单天客流      |
| line5    | int    | line5单天客流      |
| line10   | int    | line10单天客流     |
| line11   | int    | line11单天客流     |
| line12   | int    | line12单天客流     |
| line_num | int    | 线路名称（数值型） |


## 用户画像之user1,16,48,190,625,1570

接口地址：http://47.100.91.67:8001/user1
http://47.100.91.67:8001/user16
http://47.100.91.67:8001/user48
http://47.100.91.67:8001/user190
http://47.100.91.67:8001/user625
http://47.100.91.67:8001/user1570

返回格式：json

请求方式：get



请求参数说明：

| 名称                        | 类型   | 说明               |
| --------------------------- | ------ | ------------------ |
| id                          | int    | 自增主键           |
| enter_station_name          | String | 进站名           |
| enter_station_time          | String    | 进站时间      |
| out_station_name            | String    | 出站名      |
| out_station_time            | String    | 出站时间      |
| enter_line                  | String    | 进站线路      |
| enter_administrative_region | String    | 进站区域      |
| out_line                    | String    | 出站线路     |
| out_administrative_region   | String    | 出站区域     |
| week_type                   | String    | 类型（工作日，周末，节假日）     |
| timedate                    | String    | 时间 |
| week_weekday                    | String    | 星期（1,2..） |
