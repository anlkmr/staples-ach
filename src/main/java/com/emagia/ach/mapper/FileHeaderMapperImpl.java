package com.emagia.ach.mapper;

import com.emagia.ach.dto.FileHeaderDto;
import com.emagia.ach.entity.FileHeaderEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class FileHeaderMapperImpl implements FileHeaderMapper {

    @Override
    public FileHeaderEntity toEntity(FileHeaderDto dto) {
        if ( dto == null ) {
            return null;
        }

        FileHeaderEntity fileHeaderEntity = new FileHeaderEntity();

        fileHeaderEntity.setRecordtype( dto.getRecordtype() );
        fileHeaderEntity.setPrioritycode( dto.getPrioritycode() );
        fileHeaderEntity.setRtNumber( dto.getRtNumber() );
        fileHeaderEntity.setFileidImo( dto.getFileidImo() );
        try {
            if ( dto.getCreationDate() != null ) {
                fileHeaderEntity.setCreationDate( new SimpleDateFormat("yyMMdd").parse( dto.getCreationDate() ) );
            }
        }
        catch ( ParseException e ) {
            throw new RuntimeException( e );
        }
        fileHeaderEntity.setCreationTime( dto.getCreationTime() );
        fileHeaderEntity.setFileidModifier( dto.getFileidModifier() );
        fileHeaderEntity.setRecordsize( dto.getRecordsize() );
        fileHeaderEntity.setBlockingfactor( dto.getBlockingfactor() );
        fileHeaderEntity.setFormatcode( dto.getFormatcode() );
        fileHeaderEntity.setOriginatingBankImdDestName( dto.getOriginatingBankImdDestName() );
        fileHeaderEntity.setCompanyNameImdOrigName( dto.getCompanyNameImdOrigName() );
        fileHeaderEntity.setReferencecode( dto.getReferencecode() );
        fileHeaderEntity.setFileHeaderId( dto.getFileHeaderId() );

        return fileHeaderEntity;
    }

    @Override
    public FileHeaderDto toDto(FileHeaderEntity entity) {
        if ( entity == null ) {
            return null;
        }

        FileHeaderDto fileHeaderDto = new FileHeaderDto();

        fileHeaderDto.setFileHeaderId( entity.getFileHeaderId() );
        fileHeaderDto.setRecordtype( entity.getRecordtype() );
        fileHeaderDto.setPrioritycode( entity.getPrioritycode() );
        fileHeaderDto.setRtNumber( entity.getRtNumber() );
        fileHeaderDto.setFileidImo( entity.getFileidImo() );
        if ( entity.getCreationDate() != null ) {
            fileHeaderDto.setCreationDate( new SimpleDateFormat("yyMMdd").format( entity.getCreationDate() ) );
        }
        fileHeaderDto.setCreationTime( entity.getCreationTime() );
        fileHeaderDto.setFileidModifier( entity.getFileidModifier() );
        fileHeaderDto.setRecordsize( entity.getRecordsize() );
        fileHeaderDto.setBlockingfactor( entity.getBlockingfactor() );
        fileHeaderDto.setFormatcode( entity.getFormatcode() );
        fileHeaderDto.setOriginatingBankImdDestName( entity.getOriginatingBankImdDestName() );
        fileHeaderDto.setCompanyNameImdOrigName( entity.getCompanyNameImdOrigName() );
        fileHeaderDto.setReferencecode( entity.getReferencecode() );

        return fileHeaderDto;
    }

    @Override
    public List<FileHeaderEntity> toEntity(List<FileHeaderDto> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<FileHeaderEntity> list = new ArrayList<FileHeaderEntity>( dtoList.size() );
        for ( FileHeaderDto fileHeaderDto : dtoList ) {
            list.add( toEntity( fileHeaderDto ) );
        }

        return list;
    }

    @Override
    public List<FileHeaderDto> toDto(List<FileHeaderEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<FileHeaderDto> list = new ArrayList<FileHeaderDto>( entityList.size() );
        for ( FileHeaderEntity fileHeaderEntity : entityList ) {
            list.add( toDto( fileHeaderEntity ) );
        }

        return list;
    }

    @Override
    public Set<FileHeaderDto> toDto(Set<FileHeaderEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        Set<FileHeaderDto> set = new HashSet<FileHeaderDto>( Math.max( (int) ( entityList.size() / .75f ) + 1, 16 ) );
        for ( FileHeaderEntity fileHeaderEntity : entityList ) {
            set.add( toDto( fileHeaderEntity ) );
        }

        return set;
    }
}
