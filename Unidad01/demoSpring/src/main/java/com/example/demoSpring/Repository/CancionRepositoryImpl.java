package com.example.demoSpring.Repository;

import com.example.demoSpring.Model.Cancion;
import com.example.demoSpring.Model.Grupo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public class CancionRepositoryImpl implements CancionRepository{
    private final JdbcTemplate jdbcTemplate;

    public CancionRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Cancion> findAll() {
        return jdbcTemplate.query("SELECT * FROM canciones INNER JOIN grupos ON codgrupo=grupo", new CancionRowMapper());
    }

    @Override
    public Cancion findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM canciones INNER JOIN grupos ON codgrupo=grupo WHERE numCancion = ?", new CancionRowMapper(), id);
    }

    @Override
    public Cancion findByTitulo(String titulo) {
        return jdbcTemplate.queryForObject("SELECT * FROM canciones INNER JOIN grupos ON codgrupo=grupo WHERE titulo = ?", new CancionRowMapper(), titulo);
    }

    @Override
    public int save(Cancion cancion) {
        String sql = "INSERT INTO canciones (numCancion, grupo, duracion, titulo, total_votos) VALUES (?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, cancion.getNumCancion(), cancion.getGrupo(), cancion.getDuracion(), cancion.getTitulo(), cancion.getTotal_votos());
    }

    @Override
    public int update(Cancion cancion) {
        try {
            return jdbcTemplate.update("UPDATE cancion SET numCancion = ?, grupo = ?, duracion = ?, titulo = ?, total_votos = ? WHERE numCancion = ?",
                    cancion.getNumCancion(), cancion.getGrupo(), cancion.getDuracion(), cancion.getTitulo(), cancion.getTotal_votos());
        } catch (Exception e) {
            return -1;
        }
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM canciones WHERE numCancion = ?", id);
    }

    private static class CancionRowMapper implements RowMapper<Cancion> {
        @Override
        public Cancion mapRow(ResultSet rs, int rowNum) throws SQLException {
            Grupo grupo = new Grupo();
            grupo.setCodGrupo(rs.getInt("codgrupo"));
            grupo.setNombre(rs.getString("nombre"));
            grupo.setLocalidad(rs.getString("localidad"));
            grupo.setEstilo(rs.getString("estilo"));
            grupo.setEsGrupo(rs.getBoolean("esGrupo"));
            grupo.setAnnoGrab(rs.getInt("annoGrab"));
            grupo.setFechaEstreno(rs.getDate("fechaEstreno") != null ? rs.getDate("fechaEstreno").toLocalDate() : null);
            grupo.setCompania(rs.getString("compania"));

            Cancion cancion = new Cancion();
            cancion.setNumCancion(rs.getInt("numCancion"));
            cancion.setGrupo(grupo);
            Time duracion = rs.getTime("duracion") != null ? (Time) rs.getTime("duracion") : null;
            cancion.setDuracion(duracion);
            cancion.setTitulo(rs.getString("titulo"));
            cancion.setTotal_votos(rs.getInt("total_votos"));


            return cancion;
        }
    }
}


