package mybatis.mapper;

import mybatis.entity.Person;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonMapper {
    Person selectById(@Param("id") int id);

    List<Person> selectByIds(@Param("ids") List<Integer> ids);
}
