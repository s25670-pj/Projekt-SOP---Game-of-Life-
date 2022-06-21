package com.mycompany.gameoflife;

import javax.swing.SwingUtilities;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;

public class GUIOfGame extends javax.swing.JFrame {
    
    final int width = 200, height = 100;
    
    boolean[][] currentState = new boolean[height][width];
    boolean[][] nextState = new boolean[height][width];
    boolean play;
    
    Image offScreenImage;
    Graphics offScreenGraphics;
    
    
    public GUIOfGame() {
        initComponents();
    
        offScreenImage = createImage(boardPanel.getWidth(), boardPanel.getHeight());
        offScreenGraphics = offScreenImage.getGraphics();
        
        Timer time = new Timer();
        
        TimerTask task = new TimerTask(){
            public void run(){
                if(play){
                    for(int i = 0; i < height; i++){
                        for(int j = 0; j < width; j++){
                            nextState[i][j] = rules(i,j);
                        }
                    }
                    for(int i = 0; i < height; i++){
                        for(int j = 0; j < width; j++){
                            currentState[i][j] = nextState[i][j];
                        }
                    }
                    display();
                }
            }
        };
        time.scheduleAtFixedRate(task, 0, 100);
        display();
    }
    
    void display(){
        offScreenGraphics.setColor(boardPanel.getBackground());
        offScreenGraphics.fillRect(0, 0, boardPanel.getWidth(), boardPanel.getHeight());
        
        for(int i = 0 ; i < height ; i++){
            for(int j = 0 ; j < width; j++){
                if(currentState[i][j]){
                    offScreenGraphics.setColor(Color.WHITE);
                    int x = j * boardPanel.getWidth()/width;
                    int y = i * boardPanel.getHeight()/height;
                    offScreenGraphics.fillRect(x, y, boardPanel.getWidth()/width, boardPanel.getHeight()/height);
                }
            }
        }
        
        offScreenGraphics.setColor(Color.BLACK); //rysowanie siatki
        for(int i = 1; i < height;i++){
            int y = i * boardPanel.getHeight()/height;
            offScreenGraphics.drawLine(0, y, boardPanel.getWidth(), y);
        }
        for(int j = 1; j < width; j++){
            int x = j * boardPanel.getWidth()/width;
            offScreenGraphics.drawLine(x, 0, x, boardPanel.getHeight());
        }
        
        boardPanel.getGraphics().drawImage(offScreenImage,0,0,boardPanel);
    }
    
    private boolean rules(int i, int j){
        int neighbors = 0;
        
        if(j > 0){
            if(currentState[i][j-1])
                neighbors++;
            if(i > 0) 
                if(currentState[i-1][j-1])
                    neighbors++;
            if(i < height - 1)
                if(currentState[i+1][j-1])
                    neighbors++;
        }
        if(j < width - 1){
            if(currentState[i][j+1])
                neighbors++;
            if(i>0)
                if(currentState[i-1][j+1])
                    neighbors++;
            if(i<height-1)
                if(currentState[i+1][j+1])
                    neighbors++;
        }
        if(i > 0)
            if(currentState[i-1][j])
                neighbors++;
        if(i < height - 1)
            if(currentState[i+1][j])
                neighbors++;
        
        if(neighbors == 3)
            return true;
        
        if(currentState[i][j] && neighbors == 2)
            return true;
        
        return false;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        boardPanel = new javax.swing.JPanel();
        startButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        boardPanel.setBackground(new java.awt.Color(51, 51, 51));
        boardPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                boardPanelMouseDragged(evt);
            }
        });
        boardPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                boardPanelMouseClicked(evt);
            }
        });
        boardPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                boardPanelComponentResized(evt);
            }
        });

        javax.swing.GroupLayout boardPanelLayout = new javax.swing.GroupLayout(boardPanel);
        boardPanel.setLayout(boardPanelLayout);
        boardPanelLayout.setHorizontalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        boardPanelLayout.setVerticalGroup(
            boardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 347, Short.MAX_VALUE)
        );

        startButton.setText("Start");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 445, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(boardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void boardPanelMouseClicked(java.awt.event.MouseEvent evt) {                                        
        int j = width * evt.getX() / boardPanel.getWidth();
        int i = height * evt.getY() / boardPanel.getHeight();
        currentState[i][j] =! currentState[i][j];
        display();
    }                                       

    private void boardPanelComponentResized(java.awt.event.ComponentEvent evt) {                                            
        offScreenImage = createImage(boardPanel.getWidth(), boardPanel.getHeight());
        offScreenGraphics = offScreenImage.getGraphics();
        display();
    }                                           

    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        play = !play;
        if(play) startButton.setText("Stop");
        else startButton.setText("Start");
        display();
    }                                           

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        currentState = new boolean[height][width];
        display();
    }                                           

    private void boardPanelMouseDragged(java.awt.event.MouseEvent evt) {                                        
        int j = width * evt.getX() / boardPanel.getWidth();
        int i = height * evt.getY() / boardPanel.getHeight();
        
        if(SwingUtilities.isLeftMouseButton(evt))
            currentState[i][j] = true;
        else
            currentState[i][j] = false;
        display();
    }                                       

    
    public static void main(String args[]) {
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUIOfGame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify                     
    private javax.swing.JPanel boardPanel;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton startButton;
    // End of variables declaration                   

}
