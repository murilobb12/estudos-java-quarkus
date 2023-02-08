package com.api.parkingcontrol.repositories;

import com.api.parkingcontrol.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 *
 *
 * Nessa classe será criada os métodos para tratarmos no banco de dados.
 * É necessário extender a classe JpaRepository passando a classe que possui a tabela, e o identificador único no generics
 *
 *
 */


public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {


}
