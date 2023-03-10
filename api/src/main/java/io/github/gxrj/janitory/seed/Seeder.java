package io.github.gxrj.janitory.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import io.github.gxrj.janitory.core.Address.District;
import io.github.gxrj.janitory.core.Address.DistrictRepository;
import io.github.gxrj.janitory.core.Citizen.Citizen;
import io.github.gxrj.janitory.core.Citizen.CitizenRepository;
import io.github.gxrj.janitory.core.Dept.Dept;
import io.github.gxrj.janitory.core.Dept.DeptRepository;
import io.github.gxrj.janitory.core.Duty.Duty;
import io.github.gxrj.janitory.core.Duty.DutyRepository;
import io.github.gxrj.janitory.core.DutyGroup.DutyGroup;
import io.github.gxrj.janitory.core.DutyGroup.DutyGroupRepository;
import io.github.gxrj.janitory.core.PubAgent.PubAgent;
import io.github.gxrj.janitory.core.PubAgent.PubAgentRepository;

@Component
public class Seeder {

    @Autowired
    private DeptRepository deptRepository;

    @Autowired
    private DutyGroupRepository groupRepository;

    @Autowired
    private DutyRepository dutyRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private PubAgentRepository agentRepository;

    @Autowired
    private CitizenRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    @EventListener
    public void seed( ContextRefreshedEvent event ) {
        seedDept();
        seedGroup();
        seedDuty();
        seedDistrict();
        seedAgent();
        seedCitizen();
    }

    private void seedDept() {
        if( deptRepository.count() > 0 ) return;

        var dept = Dept.builder().name( "Secretaria Adjunta de Saneamento" ).build();
        deptRepository.save( dept );
    }

    private void seedGroup() {
        if( groupRepository.count() > 0 ) return;

        var group = DutyGroup.builder().name( "Agua Pluvial, Bueiros e Esgoto" ).build();
        groupRepository.save( group );
    }

    private void seedDuty() {
        if( dutyRepository.count() > 0 ) return;

        var duty = Duty.builder()
                        .name( "Bueiro sem tampa" )
                        .dept( 
                            deptRepository
                                .findByName( "Secretaria Adjunta de Saneamento" )
                                          .orElse( null ) 
                        )
                        .group( 
                            groupRepository.
                                findByName( "Agua Pluvial, Bueiros e Esgoto" )
                                    .orElse( null ) 
                        )
                        .build();

        dutyRepository.save( duty );
    }

    private void seedDistrict() {
        if( districtRepository.count() > 0 ) return;

        var district = District.builder().name( "centro" ).build();
        districtRepository.save( district );
    }   

    private void seedAgent() {
        if( agentRepository.count() > 0 ) return;

        var agent = PubAgent.builder()
                            .isAdmin( true )
                            .login( "agent" )
                            .name( "z?? das couves" )
                            .password( encoder.encode("123") )
                            .dept( 
                                deptRepository.findByName( "Secretaria Adjunta de Saneamento" )
                                                .orElse( null ) 
                            )
                            .build();

        agentRepository.save( agent );
    }

    private void seedCitizen() {
        if( userRepository.count() > 0 ) return;

        var user = Citizen.builder()
                            .email( "user@gmail.com" )
                            .name( "Jo??o Man??" )
                            .build();
        userRepository.save( user );
    }
}