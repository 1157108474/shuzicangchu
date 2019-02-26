create or replace PROCEDURE "PROC_GETMAXNUM" (iFlag integer,p_item  varchar2,maxCode out varchar2)
       as
          tempNum integer;
          maxNum integer;
          currdate integer;

begin
  tempNum  := 0;
  maxNum   := 1;
  currdate := 0;
  select to_char(sysdate, 'yyyymmdd') into currdate from dual;
  select count(*)
    into tempNum
    from ST_MAXCODE
   where ST_MAXCODE_PREFIX = p_item;

  if tempNum > 0 then
    begin
      select ST_MAXCODE_SERIAL + 1
        into maxNum
        from ST_MAXCODE
       where ST_MAXCODE_PREFIX = p_item;
      update ST_MAXCODE
         set ST_MAXCODE_SERIAL = ST_MAXCODE_SERIAL + 1
       where ST_MAXCODE_PREFIX = p_item;
    end;
  else
    begin
      insert into  ST_MAXCODE(ST_MAXCODE_PREFIX,ST_MAXCODE_SERIAL) values (p_item, 1);
    end;
  end if;
  commit;

  DBMS_OUTPUT.PUT_LINE(iFlag);
  DBMS_OUTPUT.PUT_LINE(to_char(maxNum, '0000'));
  DBMS_OUTPUT.PUT_LINE(p_item || to_char(maxNum, '0000'));
  DBMS_OUTPUT.PUT_LINE(maxNum);
  if iFlag = 1 then
    --??????
    begin
      maxCode := to_char(to_number(p_item) * 1000000 + maxNum);
    end;
  elsif iFlag = -1 then
    begin
      maxCode := p_item || trim(to_char(maxNum, '00000'));
    end;
  elsif iFlag = -2 then
    begin
      maxCode := p_item || trim(to_char(maxNum, '00'));
    end;
  else
    --??????
    begin
      --DBMS_OUTPUT.PUT_LINE(p_item || trim(to_char(maxNum,'0000')));
      --updated by ?? 2012-7-31

      maxCode := p_item || currdate || trim(to_char(maxNum, '0000'));

    end;
  end if;
exception

  when others then
    rollback;
    DBMS_OUTPUT.PUT_LINE('流水号：' || maxCode);

end;





