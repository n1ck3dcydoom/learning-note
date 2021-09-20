package mybatis.mapper;

import mybatis.entity.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PersonMapper {
    Person selectById(@Param("id") int id);
}
