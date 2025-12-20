public interface ZoneService {

    Zone createZone(Zone zone);

    Zone updateZone(Long id, Zone zone);

    Zone getZoneById(Long id);

    List<Zone> getAllZones();
}
