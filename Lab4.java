package com.company;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Date;

public class Lab4 extends JFrame {
    private JPanel panel1;
    private JTextField textField1;
    private JTree tree1;
    private JButton createFileButton;
    private JButton createDirectoryButton;
    private JButton deleteButton;
    private JButton propertiesButton;
    File Selected_file;
    DefaultMutableTreeNode Selected_node;
    DefaultTreeModel treeModel;

    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
        if (node == null)
            return;
        Object nodeInfo = node.getUserObject();
        FileNode fll = (FileNode) nodeInfo;
        Selected_file = fll.file;
        Selected_node = node;
        textField1.setText(fll.file.getAbsolutePath());
        if (!node.isLeaf()) {
            DefaultMutableTreeNode firts_node = (DefaultMutableTreeNode) node.getFirstChild();
            Object first_child_nodeInfo = firts_node.getUserObject();
            FileNode fll2 = (FileNode) first_child_nodeInfo;
            if (fll2.file.getName().equals(fll.file.getName())) {
                node.remove(0);
                createChildren(fll.file, node);
            }
        }
    }

    public void createChildren(File fileRoot, DefaultMutableTreeNode node) {
        File[] files = fileRoot.listFiles();
        if (files == null) return;
        for (File file : files) {
            DefaultMutableTreeNode childNode =
                    new DefaultMutableTreeNode(new FileNode(file));
            node.add(childNode);
            if (file.isDirectory()) {
                DefaultMutableTreeNode dummy_child = new DefaultMutableTreeNode(new FileNode(file));
                childNode.add(dummy_child);
            }
        }
    }

    public Lab4() {
        File fileRoot = new File("C://");
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);
        createChildren(fileRoot, root);
        tree1.setModel(treeModel);

        tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        tree1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (e.getClickCount() == 2) {
                    DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
                    if (node == null)
                        return;
                    Object nodeInfo = node.getUserObject();
                    FileNode fll = (FileNode) nodeInfo;
                    if (fll.file.isFile() && fll.file.exists()) {
                        Desktop desktop = Desktop.getDesktop();
                        try {
                            desktop.open(fll.file);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });

        tree1.addTreeSelectionListener(this::valueChanged);

        propertiesButton.addActionListener(e -> {
            String str = "";
            if (!textField1.getText().equals("")) {
                File fl = new File(textField1.getText());
                if (fl.isFile()) {
                    str = "Размер: " + fl.length() + "\nИзменен: " + new Date(fl.lastModified()) + " ";
                }
                if (fl.isDirectory()) {
                    str = "Содержит: " + fl.list().length + " файлов и директорий";
                }
                JOptionPane.showMessageDialog(null, str);
            }
        });

        createFileButton.addActionListener(e -> {
            String str = "";
            str = JOptionPane.showInputDialog(null,"Создание файла");
            if (!textField1.getText().equals("") && !str.equals("")) {
                File file = new File(textField1.getText() + "//" + str);
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                        Desktop desktop = Desktop.getDesktop();
                        DefaultMutableTreeNode child = new DefaultMutableTreeNode(new FileNode(file));
                        Selected_node.add(child);
                        treeModel.reload(Selected_node);
                        desktop.open(file);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }

                }
            }
        });

        createDirectoryButton.addActionListener(e -> {
            Object nodeInfo = Selected_node.getUserObject();
            FileNode fll = (FileNode) nodeInfo;
            if (fll.file.isDirectory()) {
                String str = "";
                str = JOptionPane.showInputDialog(null,"Создание директории");
                if (!str.equals("")) {
                    File fl = (new File(textField1.getText() + "//" + str));
                    fl.mkdir();
                    textField1.setText("");
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(new FileNode(fl));
                    Selected_node.add(child);
                    treeModel.reload(Selected_node);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            if (Selected_file != null) {
                File fl = Selected_file;
                int result = JOptionPane.showConfirmDialog(null, "Удалить?", "Удалить?", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    if (fl.isDirectory()) {
                        if (fl.list().length < 1) {
                            fl.delete();
                        }
                    }
                    if (fl.isFile()) {
                        fl.delete();
                    }
                    if (Selected_node != null) {
                        treeModel.removeNodeFromParent(Selected_node);
                    }
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Файловый менеджер");
        frame.setContentPane(new Lab4().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    class FileNode {
        private File file;

        public FileNode(File file) {
            this.file = file;
        }

        @Override
        public String toString() {
            String name = file.getName();
            if (name.equals("")) {
                return file.getAbsolutePath();
            } else {
                return name;
            }
        }
    }
}