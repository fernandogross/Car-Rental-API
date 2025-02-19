package com.dentsu.bootcamp.repository;

import com.dentsu.bootcamp.model.DriversLicenseEntity;
import com.dentsu.bootcamp.model.ProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository  extends JpaRepository<ProfileEntity, Long> {
    @Query("SELECT p FROM profile p WHERE p.driversLicense = :driversLicense AND p.lastName = :lastName")
    Optional<ProfileEntity> findByDriversLicenseAndLastName(
            @Param("driversLicense") DriversLicenseEntity driversLicense,
            @Param("lastName") String lastName
    );

    Optional<ProfileEntity> findByLoyaltyNumber(String loyaltyNumber);
}
