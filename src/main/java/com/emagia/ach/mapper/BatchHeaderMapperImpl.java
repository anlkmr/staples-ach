package com.emagia.ach.mapper;

import com.emagia.ach.dto.BatchHeaderDto;
import com.emagia.ach.entity.BatchHeaderEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class BatchHeaderMapperImpl implements BatchHeaderMapper {

    @Override
    public BatchHeaderEntity toEntity(BatchHeaderDto dto) {
        if ( dto == null ) {
            return null;
        }

        BatchHeaderEntity batchHeaderEntity = new BatchHeaderEntity();

        batchHeaderEntity.setRecordtype( dto.getRecordtype() );
        batchHeaderEntity.setServiceclasscode( dto.getServiceclasscode() );
        batchHeaderEntity.setCompanyNamePayeePayor( dto.getCompanyNamePayeePayor() );
        batchHeaderEntity.setCompanyDiscretionaryData( dto.getCompanyDiscretionaryData() );
        batchHeaderEntity.setCompanyid( dto.getCompanyid() );
        batchHeaderEntity.setSeccode( dto.getSeccode() );
        batchHeaderEntity.setCompanyEntryDesc( dto.getCompanyEntryDesc() );
        batchHeaderEntity.setCompanyDescDate( dto.getCompanyDescDate() );
        try {
            if ( dto.getEffectiveEntryDate() != null ) {
                batchHeaderEntity.setEffectiveEntryDate( new SimpleDateFormat("yyMMdd").parse( dto.getEffectiveEntryDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        batchHeaderEntity.setSettlementDate( dto.getSettlementDate() );
        batchHeaderEntity.setOriginatorStatusCode( dto.getOriginatorStatusCode() );
        batchHeaderEntity.setRtNumberOdfiId( dto.getRtNumberOdfiId() );
        batchHeaderEntity.setBatchnumber( dto.getBatchnumber() );
        if ( dto.getBatchHeaderId() != null ) {
            batchHeaderEntity.setBatchHeaderId( dto.getBatchHeaderId() );
        }

        return batchHeaderEntity;
    }

    @Override
    public BatchHeaderDto toDto(BatchHeaderEntity entity) {
        if ( entity == null ) {
            return null;
        }

        BatchHeaderDto batchHeaderDto = new BatchHeaderDto();

        batchHeaderDto.setBatchHeaderId( entity.getBatchHeaderId() );
        batchHeaderDto.setRecordtype( entity.getRecordtype() );
        batchHeaderDto.setServiceclasscode( entity.getServiceclasscode() );
        batchHeaderDto.setCompanyNamePayeePayor( entity.getCompanyNamePayeePayor() );
        batchHeaderDto.setCompanyDiscretionaryData( entity.getCompanyDiscretionaryData() );
        batchHeaderDto.setCompanyid( entity.getCompanyid() );
        batchHeaderDto.setSeccode( entity.getSeccode() );
        batchHeaderDto.setCompanyEntryDesc( entity.getCompanyEntryDesc() );
        batchHeaderDto.setCompanyDescDate( entity.getCompanyDescDate() );
        if ( entity.getEffectiveEntryDate() != null ) {
            try {
                batchHeaderDto.setEffectiveEntryDate( new SimpleDateFormat("yyMMdd").format( entity.getEffectiveEntryDate() ) );
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
        batchHeaderDto.setSettlementDate( entity.getSettlementDate() );
        batchHeaderDto.setOriginatorStatusCode( entity.getOriginatorStatusCode() );
        batchHeaderDto.setRtNumberOdfiId( entity.getRtNumberOdfiId() );
        batchHeaderDto.setBatchnumber( entity.getBatchnumber() );

        return batchHeaderDto;
    }

    @Override
    public List<BatchHeaderEntity> toEntity(List<BatchHeaderDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<BatchHeaderEntity> list = new ArrayList<BatchHeaderEntity>( dtoList.size() );
        for ( BatchHeaderDto batchHeaderDto : dtoList ) {
            list.add( toEntity( batchHeaderDto ) );
        }

        return list;
    }

    @Override
    public List<BatchHeaderDto> toDto(List<BatchHeaderEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<BatchHeaderDto> list = new ArrayList<BatchHeaderDto>( entityList.size() );
        for ( BatchHeaderEntity batchHeaderEntity : entityList ) {
            list.add( toDto( batchHeaderEntity ) );
        }

        return list;
    }

    @Override
    public Set<BatchHeaderDto> toDto(Set<BatchHeaderEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<BatchHeaderDto> set = new HashSet<BatchHeaderDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( BatchHeaderEntity batchHeaderEntity : entityList ) {
            set.add( toDto( batchHeaderEntity ) );
        }

        return set;
    }
}
