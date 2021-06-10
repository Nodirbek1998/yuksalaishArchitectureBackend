package uz.cas.controllersestem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.cas.controllersestem.entity.Document;

import javax.print.attribute.standard.DocumentName;

public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
