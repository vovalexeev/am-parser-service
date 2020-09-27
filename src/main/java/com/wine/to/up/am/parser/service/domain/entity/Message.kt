package com.wine.to.up.am.parser.service.domain.entity

import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "messages")
@Setter
@Getter
@NoArgsConstructor
class Message(private val content: String? = null) {
    @Id
    private val id = UUID.randomUUID()
}