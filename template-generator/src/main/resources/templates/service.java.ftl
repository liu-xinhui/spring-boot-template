package ${package.Service};

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import ${svoPackage}.${entity}Svo;
import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${superServiceClassPackage};
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.AllArgsConstructor;

/**
 * ${table.comment!} 服务类
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
@Service
@Transactional
@AllArgsConstructor
public class ${table.serviceName} extends ${superServiceClass}<${entity}> {
    <#assign mapperNameLow = table.mapperName?uncap_first>

    private final ${table.mapperName} ${mapperNameLow};

    //todo 自行修改
    public Page<${entity}> selectWithPage(${entity}Svo svo) {
       return ${mapperNameLow}.selectWithPage(svo.getPage(), svo);
    }
}
</#if>
