package mybatis.mapper;

import mybatis.entity.Person;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
    Person selectById(@Param("id") int id);
}
