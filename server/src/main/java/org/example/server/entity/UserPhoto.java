package org.example.server.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = "bytes")
@Table(name = "user_photo")
public class UserPhoto extends Base {

    @Lob
    @Column(name = "bytes", nullable = false)
    private Byte[] bytes;

    @Column(name = "content_type", nullable = false)
    private String contentType;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    private User user;

    public void setBytes(Byte[] bytes) {
        this.bytes = new Byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            this.bytes[i] = bytes[i];
        }
    }

    public Byte[] getBytesAsObject() {
        Byte[] byteObjects = new Byte[bytes.length];
        int i = 0;
        for (Byte b : bytes)
            byteObjects[i++] = b;
        return byteObjects;
    }
}
