package com.restwebservice.cathibot.service;

import com.restwebservice.cathibot.dao.CustomerDao;
import com.restwebservice.cathibot.dao.FileDao;
import com.restwebservice.cathibot.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;

@Service
public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    FileDao fileDao;

    public void updateDirectory() {
        ArrayList<String> folderPathAddress = new ArrayList<>();
        Date today = Calendar.getInstance().getTime();
        File from = new File("C:\\Users\\zackb\\Desktop\\GSA\\ClientsNewFiles");
        File to = new File("C:\\Users\\zackb\\Desktop\\GSA\\Clients");
        File basePath = to;
        String[] tempDirectories = from.list((current, name) -> new File(current, name).isDirectory());
        for (int i = 0; i < tempDirectories.length; i++)
        {
            moveFilesFromDirToDir(from.toString() + "\\\\\\\\" + tempDirectories[i], to.toString());
        }
        String[] directories = basePath.list((current, name) -> new File(current, name).isDirectory());
        Arrays.sort(directories);
        fetchFiles(basePath, f ->
                folderPathAddress.add(f.getAbsolutePath().replaceAll("\\\\", "\\\\\\\\"))
        );
        for (int i = 0; i < directories.length; i++)
        {
            int count = 0;
            for (int j = 0; j < folderPathAddress.size(); j++)
            {
                String client = StringUtils.substringBetween(folderPathAddress.get(j), "Clients\\\\", "\\\\");

                if (directories[i].equals(client))
                {
                    count++;
                }
            }
            updateCustomers(directories[i], count, i + 1, today);
        }

//         int columnPointer = directories.length + 1;
//         while (hd.checkLeftover(columnPointer))
//         {
//             hd.deleteRemainder(columnPointer);
//             columnPointer++;
//         }

        // for (int j = 0; j < folderPathAddress.size(); j++)
        // {
        // System.out.println(folderPathAddress.get(j));
        // }
        // System.out.println(Arrays.toString(directories));
    }

    public void updateCustomers(String client, int fileCount, int clientNo, Date date)
    {
        Customer customer = customerDao.findByCustomerId(client);
        if (customer != null)
        {
            customer.setReceivedFiles(fileCount);
        }
        else
        {
            customer = new Customer();
            customer.setCustomerId(client);
            customer.setReceivedFiles(fileCount);
            customer.setDateTransfered(date);
        }
        customerDao.save(customer);
    }

    public static void fetchFiles(File dir, Consumer<File> fileConsumer)
    {
        if (dir.isDirectory())
        {
            for (File file1 : dir.listFiles())
            {
                fetchFiles(file1, fileConsumer);
            }
        }
        else
        {
            fileConsumer.accept(dir);
        }
    }

    public void moveFilesFromDirToDir(String srcDirPath, String destDirPath)
    {
        try
        {
            if(StringUtils.isNoneBlank(srcDirPath) && StringUtils.isNoneBlank(destDirPath))
            {
                File srcDirFile = new File(srcDirPath);
                File destDirFile = new File(destDirPath);
                FileUtils.moveDirectoryToDirectory(srcDirFile, destDirFile, true);
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
