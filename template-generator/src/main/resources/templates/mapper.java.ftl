package ${package.Mapper};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import ${svoPackage}.${entity}Svo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
@Repository
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    //todo xml自行实现，不需要则删除
    Page<${entity}> selectWithPage(Page<${entity}> page, @Param("svo") ${entity}Svo svo);
}
</#if>
