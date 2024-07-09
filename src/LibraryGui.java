import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LibraryGui extends JFrame {
    DefaultTableModel tableModel;
    Library library;
    JTextField tname=new JTextField();
    JTextField tauthor=new JTextField();
    JTextField tISBN=new JTextField();
    public LibraryGui(){
        this.library=new Library();
        setLayout(new BorderLayout());
        setSize(500,500);

        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(3,2));

        JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayout(3,2));
        JLabel name=new JLabel("Name");
        panel2.add(name);

        panel2.add(tname);
        JLabel author=new JLabel("Author");
        panel2.add(author);

        panel2.add(tauthor);
        JLabel ISBN=new JLabel("ISBN");
        panel2.add(ISBN);

        panel2.add(tISBN);



        tableModel = new DefaultTableModel(new Object[]{"Name", "Author", "ISBN","Borrowed"}, 0);
        JTable table=new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);


        JButton addBook=new JButton("add book");
        panel.add(addBook);
        addBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tname.getText().trim().isEmpty() || tauthor.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both Name and Author.");
                }else {
                    if (checkISBN()) {
                        Book book = new Book(tname.getText(), tauthor.getText(), tISBN.getText());
                        library.addBook(book);
                        updateTable();
                        clearFields();
                    }
                }

            }
        });

        JButton removeBook=new JButton("remove book");
        panel.add(removeBook);
        removeBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkISBN()) {
                    String isbn = tISBN.getText();
                    Book book = library.findBook(isbn);
                    if (book != null) {
                        library.removeBook(isbn);
                        updateTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Book with ISBN " + isbn + " not found.");
                    }
                }
                clearFields();
            }
        });


        JButton listBooks=new JButton("list books");
        panel.add(listBooks);
        listBooks.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTable();
            }
        });

        JButton borrowBook=new JButton("borrow book");
        panel.add(borrowBook);
        borrowBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(checkISBN()) {
                    if (library.findBook(tISBN.getText()) != null) {
                        library.borrowBook(tISBN.getText());
                        updateTable();
                    } else {
                        JOptionPane.showMessageDialog(null, "Book with ISBN " + tISBN.getText() + " not found.");
                    }
                }
                clearFields();
            }
        });

        JButton returnBook=new JButton("return book");
        panel.add(returnBook);
        returnBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               if (checkISBN()) {
                   if (library.findBook(tISBN.getText()) != null) {
                       library.returnBook(tISBN.getText());
                       updateTable();
                   } else {
                       JOptionPane.showMessageDialog(null, "Book with ISBN " + tISBN.getText() + " not found.");
                   }
               }
                clearFields();
            }
        });

        JButton findBook=new JButton("find book");
        panel.add(findBook);
        findBook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkISBN()) {
                    Book book = library.findBook(tISBN.getText());
                    tableModel.setRowCount(0);
                    String Borrowed;
                    if (book != null) {
                        if (book.isBorrowed()) {
                            Borrowed = "yes";
                        } else {
                            Borrowed = "no";
                        }
                        tableModel.addRow(new Object[]{book.getName(), book.getAuthor(), book.getISBN(), Borrowed});
                    } else {
                        JOptionPane.showMessageDialog(null, "Book with ISBN " + tISBN.getText() + " not found.");
                    }
                }
                clearFields();
            }
        });



        add(scrollPane, BorderLayout.CENTER);
        add(panel2,BorderLayout.NORTH);
        add(panel,BorderLayout.SOUTH);
        setVisible(true);


    }
    private void clearFields(){
        tname.setText("");
        tauthor.setText("");
        tISBN.setText("");
    }
    private void updateTable(){
        tableModel.setRowCount(0);

        for (Book book1:library.getBooks()){
            String Borrowed;
            if (book1.isBorrowed()){
                Borrowed="yes";
            }else {
                Borrowed="no";
            }
            tableModel.addRow(new Object[]{book1.getName(),book1.getAuthor(),book1.getISBN(),Borrowed});
        }

    }
    private boolean checkISBN(){
        try {
            Integer.parseInt(tISBN.getText());
            return true;
        }catch (NumberFormatException ex){
            JOptionPane.showMessageDialog(null,"Please enter a numeric value for ISBN");
            return false;
        }
    }

}
