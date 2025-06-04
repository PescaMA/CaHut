package org.service;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {
    private static final String AUDIT_FILE = "audit/audit.csv";
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd: HH:mm:ss");

    public static void save(String message){
        String timestamp = LocalDateTime.now().format(format);
        String[] HEADERS = { "timestamp", "action"};

        boolean writeHeaders = !new File(AUDIT_FILE).exists() || new File(AUDIT_FILE).length() == 0;

        try (FileWriter out = new FileWriter(AUDIT_FILE, true); ///  append
             CSVPrinter printer = new CSVPrinter(out,
                     CSVFormat.DEFAULT.builder().setHeader(writeHeaders ? HEADERS : null).build());
        ) {
            printer.printRecord(timestamp, message);
        } catch (IOException e){
            e.printStackTrace(System.out);
            System.out.println("error writing to audit");
        }
    }
}
