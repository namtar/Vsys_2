package de.htw.berlin.student.vsys2.rpc;

import de.htw.berlin.student.vsys2.rpc.business.MultiServerRunnable;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.Writer;
import java.net.Socket;

/**
 * Tests for the {@link de.htw.berlin.student.vsys2.rpc.business.MultiServerRunnable}.
 *
 * @author by Matthias Drummer on 18.11.2014
 */
public class MultiServerRunnableTest {

    private MultiServerRunnable testInstance;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    @Before
    public void before() throws IOException {

        this.testInstance = new MultiServerRunnable(outputStream, inputStream);
    }

    @Test
    public void testCommunication() throws IOException {

//        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
//        oStream.write("quit".getBytes());
//
//        outputStream = new ObjectOutputStream(new ByteArrayOutputStream());
//        inputStream = new ObjectInputStream(new ByteArrayInputStream(oStream.toByteArray()));
//
//        Thread t = new Thread(testInstance);
//        t.run();


    }
}
