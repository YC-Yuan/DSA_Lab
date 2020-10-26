/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package lab_five.hashtabletest;

import lab_five.generator.KeyGenerator;
import lab_five.generator.PermutedStringGenerator;
import lab_five.generator.RandomIntegerGenerator;
import lab_five.generator.RandomStringGenerator;
import lab_five.gui.GUIPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.TitledBorder;

/**
 *
 * @author tcolburn
 */
public class TestPanel extends JPanel {

    public TestPanel() {

        integerGenerator = new RandomIntegerGenerator();
        stringGenerator = new RandomStringGenerator();
        similarStringGenerator = new PermutedStringGenerator();

        guiPanel = new GUIPanel();

        integerButton = new JRadioButton("Random Integers");
        stringButton = new JRadioButton("Random Strings");
        similarStringButton = new JRadioButton("Permuted Strings");

        integerButton.addActionListener(makeButtonListener(integerGenerator));
        stringButton.addActionListener(makeButtonListener(stringGenerator));
        similarStringButton.addActionListener(makeButtonListener(similarStringGenerator));

        group = new ButtonGroup();

        group.add(integerButton);
        group.add(stringButton);
        group.add(similarStringButton);

        group.setSelected(integerButton.getModel(), true);
        guiPanel.setKeyGenerator(integerGenerator);

        controlPanel = new JPanel();
        controlPanel.setPreferredSize(new Dimension(200,100));
        controlPanel.setLayout(new GridLayout(3, 1, 0, 5));
        controlPanel.setBorder(new TitledBorder("Key Type"));

        controlPanel.add(integerButton);
        controlPanel.add(stringButton);
        controlPanel.add(similarStringButton);

        add(guiPanel);
        add(controlPanel);
    }

    private ActionListener makeButtonListener(final KeyGenerator keyGenerator) {
        return new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                keyGenerator.reset();
                guiPanel.setKeyGenerator(keyGenerator);
                guiPanel.clear();
            }
        };
    }

    private GUIPanel guiPanel;

    private ButtonGroup group = new ButtonGroup();

    private JRadioButton integerButton;
    private JRadioButton stringButton;
    private JRadioButton similarStringButton;

    private JPanel controlPanel;

    private KeyGenerator integerGenerator;
    private KeyGenerator stringGenerator;
    private KeyGenerator similarStringGenerator;

}
