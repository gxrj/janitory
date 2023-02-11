package io.github.gxrj.janitory.core.Call;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CallRepository extends JpaRepository<Call, UUID> { 
    
    Optional<Call> findByProtocol( String protocol );

    List<Call> findByStatus( Status status );
    List<Call> findByDuty_Name( String dutyName );
    List<Call> findByAddress_ZipCode( String zipCode );
    List<Call> findByAddress_PubPlace( String pubPlace );
    List<Call> findByAddress_District_Name( String district );
    List<Call> findByDestination_Name( String deptName );
    List<Call> findByAuthor_Email( String email );

    List<Call> findByCreationDateBetweenAndFindByDestionation_Name( 
                            LocalDateTime start, LocalDateTime end, String destinationName );
}
