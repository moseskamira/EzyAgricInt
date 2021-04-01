package com.team295.ezyagric.util;

public interface DataMapperInterface <Entity, DomainModel>{

    DomainModel mapFromEntity(Entity entity);

    Entity mapToEntity(DomainModel domainModel);
}
