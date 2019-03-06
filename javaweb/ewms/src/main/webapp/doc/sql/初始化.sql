
--菜单初始化--
Insert into BASE_MENU (CODE,NAME,PARENTCODE,TYPE,BUTTONMODE,URL,ICON,ICONURL,SORT,STATUS,MEMO,CREATOR,CREATEDATE,UPDATER,UPDATEDATE,AUTHIDENTITY) values ('product','产品库管理','0',1,3,null,'fa-cubes',null,9,1,null,1010,to_date('01-11-2018 09:41:09','dd-mm-yyyy hh24:mi:ss'),null,null,null);
Insert into BASE_MENU (CODE,NAME,PARENTCODE,TYPE,BUTTONMODE,URL,ICON,ICONURL,SORT,STATUS,MEMO,CREATOR,CREATEDATE,UPDATER,UPDATEDATE,AUTHIDENTITY) values ('MesRk','产品入库','product',2,1,'/mes/manageMesRk.htm','fa-cube',null,1,1,null,1010,to_date('01-11-2018 09:42:24','dd-mm-yyyy hh24:mi:ss'),null,null,null);
Insert into BASE_MENU (CODE,NAME,PARENTCODE,TYPE,BUTTONMODE,URL,ICON,ICONURL,SORT,STATUS,MEMO,CREATOR,CREATEDATE,UPDATER,UPDATEDATE,AUTHIDENTITY) values ('MesCk','产品出库','product',2,1,'/mes/manageMesCk.htm','fa-cube',null,2,1,null,1010,to_date('01-11-2018 09:43:22','dd-mm-yyyy hh24:mi:ss'),null,null,null);

--供应商管理--
update Base_Provider set deleted = 1 where deleted is null;
update Base_Provider set add_type = 0 where add_type is null;
alter table Base_Provider modify deleted default 1;
alter table Base_Provider modify add_type  default 0;
--使用单位管理--
update BASE_USEDEP set deleted = 1 where deleted is null;
update BASE_USEDEP set add_type = 0 where add_type is null;
alter table BASE_USEDEP modify deleted default 1;
alter table BASE_USEDEP modify add_type  default 0;
--申请单位管理--
update BASE_APPLYDEP set deleted = 1 where deleted is null;
update BASE_APPLYDEP set add_type = 0 where add_type is null;
alter table BASE_APPLYDEP modify deleted default 1;
alter table BASE_APPLYDEP modify add_type  default 0;
--物料范围--
update BASE_OFFICES_SCOPE set add_type = 0 where add_type is null;
alter table BASE_OFFICES_SCOPE modify add_type  default 0;
commit;
-- 数据字典

insert into BASE_DICTIONARY (ID, GUID, CODE, NAME, PARENTID, LEVELCOUNT, LEVELCODE, ENDFLAG, STATUS )
values (basedictionary_sequence.nextval, 'null', ' ', '数据字典分类', null,  null, null, null, 1 );
commit;
update base_dictionary set id = 0 where id =(select max(id) from base_dictionary);;
commit;


--任务管理

-- Add comments to the columns 
comment on column INF_TASK.status is '状态';
comment on column INF_TASK.addtype is '新增类型';


--物料分类

-- Add comments to the columns 
comment on column BASE_SPAREPARTSCATE.addtype
  is '新增类型';



--物料
-- Add comments to the columns 
comment on column BASE_MATERIAL.addtype is '新增类型';

--update base_material set models = model；
--commit;

--库房库区
--alter table BASE_WAREHOUSE rename column extendint1 to ERPID;
-- Add comments to the columns 
comment on column BASE_WAREHOUSE .addtype  is '新增类型';

insert into BASE_WAREHOUSE (ID, GUID, CODE, NAME, PARENTID, LEVELCOUNT, LEVELCODE, ENDFLAG, STATUS)
values (basewarehouse_sequence.nextval, null, '', '仓库信息', null, 1, null, null,1);
commit;

update BASE_WAREHOUSE set id = 0 where id =(select max(id) from BASE_WAREHOUSE );
commit;

