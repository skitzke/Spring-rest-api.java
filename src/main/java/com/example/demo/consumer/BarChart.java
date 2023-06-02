package com.example.demo.consumer;

import com.example.demo.exceptions.ReportYearNotAvailable;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.LegendItem;
import org.jfree.chart.LegendItemCollection;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.ApplicationFrame;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import java.awt.*;

/**
 * This class is responsible for visualizing the Netflix movie average vote on IMDB and Happiness report for the country and year of release
 */
public class BarChart extends ApplicationFrame {

    RestClient restClient;

    public BarChart(final String title) throws ReportYearNotAvailable {
        super(title);
        this.restClient = new RestClient();
        final CategoryDataset avgVote = averageVoteInIMDB();
        final CategoryDataset happinessRank = happinessRankByCountryAndYear();
        final JFreeChart chart = createChart(avgVote, happinessRank);
        final ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(600, 300));
        setContentPane(chartPanel);
    }

    private CategoryDataset averageVoteInIMDB() throws ReportYearNotAvailable {
        double[] result = restClient.showHappinessReportAndImdbRating("One Day");
        double[] secondResult = restClient.showHappinessReportAndImdbRating("White Island");
        final String series1 = "Average Vote in IMDB";
        final String series2 = "";

        final String category = "" + (int) result[2] + ", One day, " + restClient.getNetflixMovieCountry("One Day");

        final String category2 = "" + (int) secondResult[2] + ", White Island, " + restClient.getNetflixMovieCountry("White Island");
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(result[0], series1, category);
        dataset.addValue(secondResult[0], series1, category2);

        dataset.addValue(null, series2, category);
        dataset.addValue(null, series2, category2);
        return dataset;

    }

    private CategoryDataset happinessRankByCountryAndYear() throws ReportYearNotAvailable {
        double[] result = restClient.showHappinessReportAndImdbRating("One Day");
        double[] secondResult = restClient.showHappinessReportAndImdbRating("White Island");

        final String series1 = "";
        final String series2 = "Happiness Score";
        final String category = "" + (int) result[2] + ", One Day, " + restClient.getNetflixMovieCountry("One Day");
        final String category2 = "" + (int) secondResult[2] + ", White Island, " + restClient.getNetflixMovieCountry("White Island");

        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(null, series1, category);
        dataset.addValue(null, series1, category2);

        dataset.addValue(result[1], series2, category);
        dataset.addValue(secondResult[0], series2, category2);

        return dataset;

    }

    private JFreeChart createChart(final CategoryDataset dataset1, final CategoryDataset dataset2){
        final CategoryAxis domainAxis = new CategoryAxis("Year of release, Netflix title, Country of release");
        final NumberAxis rangeAxis = new NumberAxis("Average Vote");
        final BarRenderer renderer1 = new BarRenderer();
        final CategoryPlot plot = new CategoryPlot(dataset1, domainAxis, rangeAxis, renderer1) {

            public LegendItemCollection getLegendItems() {

                final LegendItemCollection result = new LegendItemCollection();

                final CategoryDataset data = getDataset();
                if (data != null) {
                    final CategoryItemRenderer r = getRenderer();
                    if (r != null) {
                        final LegendItem item = r.getLegendItem(0, 0);
                        result.add(item);
                    }
                }

                final CategoryDataset dset2 = getDataset(1);
                if (dset2 != null) {
                    final CategoryItemRenderer renderer2 = getRenderer(1);
                    if (renderer2 != null) {
                        final LegendItem item = renderer2.getLegendItem(1, 1);
                        result.add(item);
                    }
                }
                return result;
            }
        };

        final JFreeChart chart = new JFreeChart("Average vote on IMDB and Happiness Rank based on release year and country by Netflix movie", plot);
        chart.setBackgroundPaint(Color.white);
        plot.setBackgroundPaint(new Color(0xEE, 0xEE, 0xFF));
        plot.setDomainAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        plot.setDataset(1, dataset2);
        plot.mapDatasetToRangeAxis(1, 1);
        final ValueAxis axis2 = new NumberAxis("Happiness Score");
        plot.setRangeAxis(1, axis2);
        plot.setRangeAxisLocation(1, AxisLocation.BOTTOM_OR_RIGHT);
        final BarRenderer renderer2 = new BarRenderer();
        plot.setRenderer(1, renderer2);

        return chart;
    }

    public static void main(final String[] args) throws ReportYearNotAvailable {
        final BarChart demo = new BarChart("Bar chart");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
