package com.cw.services;

import com.cw.models.Registro;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class LimparVolume {
    private Alerta alerta;

    Registro registroVolume = new Registro();
    /*Path pastaTemp = Paths.get("C:\\Users\\Alfa User\\AppData\\Local\\Temp");*/
    Path pastaTemp = Paths.get("C:\\Users\\Alfa User\\Documents\\testeLimpar");

    public void iniciarLimpezaVolume() {
        new Thread(threadLimparTemp).start();
    }

    private Runnable threadLimparTemp = new Runnable() {

        public void run() {

            try {
                Thread.sleep(10000);
                //if (alerta.verificarAlerta(registroVolume))
                if (true) {
                    while (true) {
                        Files.walkFileTree(pastaTemp, new SimpleFileVisitor<Path>() {
                            @Override
                            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                                System.out.println("Fechando arquivo: " + file.getFileName());
                                Files.delete(file);
                                return FileVisitResult.CONTINUE;
                            }
                            @Override
                            public FileVisitResult postVisitDirectory(Path pasta, IOException exc) throws IOException {
                                if (!pasta.equals(pastaTemp)){
                                    System.out.println("Fechando pasta: "+ pasta.getFileName());
                                    Files.delete(pasta);

                                }
                                return FileVisitResult.CONTINUE;
                            }
                        });
                        Thread.sleep(10000);
                    }
                }

            } catch (Exception e) {
                System.out.println("Não foi possivel executar a limpeza " + e.getMessage());
            }
        }
    };
}






