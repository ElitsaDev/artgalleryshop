package bg.softuni.artgalleryshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "statistics")
public class StatisticEntity extends BaseEntity{
    @Column(nullable = false, name = "local_date_time")
    private LocalDateTime localDateTime;

    @Column(nullable = false)
    private String ipAddress;

    public StatisticEntity() {
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
